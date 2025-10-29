package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.instituciones.ControladorInstitucionesWSService;
import cliente.ws.instituciones.IControladorInstitucionesWS;
import cliente.ws.instituciones.DataInstitucion;
import cliente.ws.instituciones.Nivel;
import cliente.ws.eventos.*;
import cliente.ws.instituciones.*;


@WebServlet("/AltaPatrocinio")
public class AltaPatrocinio extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorInstitucionesWS IInst_WS;
    private IControladorEventoWS IEV_WS;
    
    @Override
    public void init() throws ServletException {
    	ControladorInstitucionesWSService servicio = new ControladorInstitucionesWSService();
        IInst_WS = servicio.getControladorInstitucionesWSPort();
        ControladorEventoWSService servicio2 = new ControladorEventoWSService();
        IEV_WS = servicio2.getControladorEventoWSPort();
        System.out.println("AltaPatrocinioWS");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener evento y edición de la URL
        String evento = request.getParameter("evento");
        String edicion = request.getParameter("edicion");
        
        if (evento != null) {
            evento = URLDecoder.decode(evento, StandardCharsets.UTF_8);
        }
        
        if (edicion != null) {
            edicion = URLDecoder.decode(edicion, StandardCharsets.UTF_8);
        }
        
        // Validar que vengan evento y edición
        if (evento == null || edicion == null) {
            request.setAttribute("error", "Debe especificar un evento y una edición");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }
        
        try {
            // Cargar tipos de registro de la edición
            List<String> tiposRegistro = IEV_WS.obtenerTipoRegistrosEdicion(evento, edicion).getItem();
            request.setAttribute("tiposRegistro", tiposRegistro);
            
            // Cargar instituciones
            List<DataInstitucion> i= IInst_WS.listarInstituciones().getItem();
            DataInstitucion[] instituciones = i.toArray(new DataInstitucion[0]);
            request.setAttribute("instituciones", instituciones);
            
            // Cargar niveles de patrocinio
            request.setAttribute("niveles", Nivel.values());
            
            // Pasar evento y edición al JSP
            request.setAttribute("evento", evento);
            request.setAttribute("edicion", edicion);
            
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron cargar los datos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	        return;
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
        
        if (evento != null) {
            evento = URLDecoder.decode(evento, StandardCharsets.UTF_8);
        }
        
        if (edicion != null) {
            edicion = URLDecoder.decode(edicion, StandardCharsets.UTF_8);
        }

        // Validación de campos vacíos
        if (evento == null || evento.isBlank() || 
            edicion == null || edicion.isBlank() ||
            tipoRegistro == null || tipoRegistro.isBlank() ||
            institucion == null || institucion.isBlank() ||
            nivelStr == null || nivelStr.isBlank() ||
            montoStr == null || montoStr.isBlank() ||
            cantidadCuposStr == null || cantidadCuposStr.isBlank() ||
            codigo == null || codigo.isBlank()) {
            
            recargarFormularioConError(request, response, evento, edicion, "No puede haber campos vacíos");
            return;
        }

        try {
            float monto = Float.parseFloat(montoStr);
            int cantidadCupos = Integer.parseInt(cantidadCuposStr);
            Nivel nivel = Nivel.valueOf(nivelStr);

            // Validaciones de negocio
            if (monto <= 0) {
                recargarFormularioConError(request, response, evento, edicion, "El monto debe ser un valor positivo");
                return;
            }

            if (cantidadCupos <= 0) {
                recargarFormularioConError(request, response, evento, edicion, "La cantidad de cupos debe ser un valor positivo");
                return;
            }

            // Validación del 20%
            DataTRegistro dataTRegistro = IEV_WS.getDataTRegistro(evento, edicion, tipoRegistro);
            float costoTotalRegistros = cantidadCupos * dataTRegistro.getCosto();

            if (costoTotalRegistros > (0.2f * monto)) {
                recargarFormularioConError(request, response, evento, edicion, 
                    "El costo de los registros gratuitos supera el 20% del aporte económico");
                return;
            }

            // Crear el patrocinio
            DataPatrocinio patrocinio = new DataPatrocinio();
            patrocinio.setCod(codigo);
            patrocinio.setCtdCupo(cantidadCupos);
            patrocinio.setFecha(LocalDate.now().toString());
            patrocinio.setMonto(monto);
            patrocinio.setNivel(nivel);

            // Llamar al controlador
            IInst_WS.nuevoPatrocinio(patrocinio, institucion, evento, edicion, tipoRegistro);

            // Redirigir con mensaje de éxito
            session.setAttribute("mensaje", "El patrocinio se ha registrado exitosamente");
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (NumberFormatException ex) {
            recargarFormularioConError(request, response, evento, edicion, 
                "Error en los datos numéricos. Verifique el monto y cantidad de cupos.");
            
        //} catch (PatrocinioRepetidoException ex) { NICOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        //    recargarFormularioConError(request, response, evento, edicion, 
        //        "Esta institución ya está patrocinando esta edición.");
            
        } catch (Exception ex) {
            recargarFormularioConError(request, response, evento, edicion, 
                "Error al crear el patrocinio: " + ex.getMessage());
        }
    }

    private void recargarFormularioConError(HttpServletRequest request, HttpServletResponse response, 
            String evento, String edicion, String error) throws ServletException, IOException {
        
        // Recargar los datos para los combos
        try {
            List<String> tiposRegistro = IEV_WS.obtenerTipoRegistrosEdicion(evento, edicion).getItem();
            request.setAttribute("tiposRegistro", tiposRegistro);
            
            DataInstitucion[] instituciones = (IInst_WS.listarInstituciones().getItem()).toArray(new DataInstitucion[0]);
            request.setAttribute("instituciones", instituciones);
            
            request.setAttribute("niveles", Nivel.values());
            
            // Mantener evento y edición
            request.setAttribute("evento", evento);
            request.setAttribute("edicion", edicion);
            
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo hacer la recarga");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	        return;
		}
        
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/altaPatrocinio.jsp").forward(request, response);
    }
}