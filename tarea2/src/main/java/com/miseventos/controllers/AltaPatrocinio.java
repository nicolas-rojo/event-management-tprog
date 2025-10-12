package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import logica.Fabrica;
import logica.interfaces.IEventos;
import logica.interfaces.IInstituciones;
import logica.datatypes.*;
import excepciones.PatrocinioRepetidoException;

@WebServlet("/AltaPatrocinio")
public class AltaPatrocinio extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEventos IEV;
    private IInstituciones IInst;

    @Override
    public void init() throws ServletException {
        IEV = Fabrica.getInstance().getIControladorEventos();
        IInst = Fabrica.getInstance().getIControladorInstituciones();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        // Si es una petición para cargar ediciones
        if ("cargarEdiciones".equals(accion)) {
            String evento = request.getParameter("evento");
            try {
                List<String> ediciones = IEV.listarEdiciones(evento);
                request.setAttribute("ediciones", ediciones);
                request.setAttribute("eventoSeleccionado", evento);
            } catch (Exception e) {
                request.setAttribute("error", "Error al cargar ediciones: " + e.getMessage());
            }
        }
        
        // Si es una petición para cargar tipos de registro
        if ("cargarTiposRegistro".equals(accion)) {
            String evento = request.getParameter("evento");
            String edicion = request.getParameter("edicion");
            try {
                List<String> ediciones = IEV.listarEdiciones(evento);
                List<String> tiposRegistro = IEV.listarTRegistros(evento, edicion);
                request.setAttribute("ediciones", ediciones);
                request.setAttribute("tiposRegistro", tiposRegistro);
                request.setAttribute("eventoSeleccionado", evento);
                request.setAttribute("edicionSeleccionada", edicion);
            } catch (Exception e) {
    			e.printStackTrace();
    	        request.setAttribute("error", "No se pudieron cargar los tipos de registro");
    	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    		}
        }
        
        // Cargar datos iniciales para los combos
        try {
            // Cargar eventos
            List<String> eventos = IEV.listarEventos();
            request.setAttribute("eventos", eventos);
            
            // Cargar instituciones
            DataInstitucion[] instituciones = IInst.listarInstituciones();
            request.setAttribute("instituciones", instituciones);
            
            // Cargar niveles de patrocinio (enum Nivel)
            request.setAttribute("niveles", Nivel.values());
            
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron cargar los datos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
        
        request.getRequestDispatcher("/WEB-INF/altaPatrocinio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String evento = request.getParameter("evento");
        String edicion = request.getParameter("edicion");
        String tipoRegistro = request.getParameter("tipoRegistro");
        String institucion = request.getParameter("institucion");
        String nivelStr = request.getParameter("nivel");
        String montoStr = request.getParameter("monto");
        String cantidadCuposStr = request.getParameter("cantidadCupos");
        String codigo = request.getParameter("codigo");

        // Validación de campos vacíos
        if (evento == null || evento.isBlank() || 
            edicion == null || edicion.isBlank() ||
            tipoRegistro == null || tipoRegistro.isBlank() ||
            institucion == null || institucion.isBlank() ||
            nivelStr == null || nivelStr.isBlank() ||
            montoStr == null || montoStr.isBlank() ||
            cantidadCuposStr == null || cantidadCuposStr.isBlank() ||
            codigo == null || codigo.isBlank()) {
            
            recargarFormularioConError(request, response, "No puede haber campos vacíos");
            return;
        }

        try {
            float monto = Float.parseFloat(montoStr);
            int cantidadCupos = Integer.parseInt(cantidadCuposStr);
            Nivel nivel = Nivel.valueOf(nivelStr);

            // Validaciones de negocio
            if (monto <= 0) {
                recargarFormularioConError(request, response, "El monto debe ser un valor positivo");
                return;
            }

            if (cantidadCupos <= 0) {
                recargarFormularioConError(request, response, "La cantidad de cupos debe ser un valor positivo");
                return;
            }

            // Validación del 20%
            DataTRegistro dataTRegistro = IEV.getDataTRegistro(evento, edicion, tipoRegistro);
            float costoTotalRegistros = cantidadCupos * dataTRegistro.getCosto();

            if (costoTotalRegistros > (0.2f * monto)) {
                recargarFormularioConError(request, response, 
                    "El costo de los registros gratuitos supera el 20% del aporte económico");
                return;
            }

            // Crear el patrocinio
            DataPatrocinio patrocinio = new DataPatrocinio(
                LocalDate.now(), // Fecha actual
                monto,
                nivel,
                codigo,
                cantidadCupos
            );

            // Llamar al controlador
            IInst.nuevoPatrocinio(patrocinio, institucion, evento, edicion, tipoRegistro);

            // Redirigir con mensaje de éxito
            session.setAttribute("mensaje", "El patrocinio se ha registrado exitosamente");
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (NumberFormatException ex) {
            recargarFormularioConError(request, response, 
                "Error en los datos numéricos. Verifique el monto y cantidad de cupos.");
            
        } catch (PatrocinioRepetidoException ex) {
            recargarFormularioConError(request, response, 
                "Ya existe un patrocinio con ese código para esta institución.");
            
        } catch (Exception ex) {
            recargarFormularioConError(request, response, 
                "Error al crear el patrocinio: " + ex.getMessage());
        }
    }

    private void recargarFormularioConError(HttpServletRequest request, HttpServletResponse response, String error) 
            throws ServletException, IOException {
        
        // Recargar los datos para los combos
        try {
            List<String> eventos = IEV.listarEventos();
            request.setAttribute("eventos", eventos);
            
            DataInstitucion[] instituciones = IInst.listarInstituciones();
            request.setAttribute("instituciones", instituciones);
            
            request.setAttribute("niveles", Nivel.values());
            
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo hacer la recarga");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
        
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/altaPatrocinio.jsp").forward(request, response);
    }
}