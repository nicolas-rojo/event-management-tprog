package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.IInstituciones;
import logica.datatypes.DataInstitucion;
import excepciones.InstitucionRepetidaException;

@WebServlet("/AltaInstitucion")
public class AltaInstitucion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IInstituciones IInst;

    @Override
    public void init() throws ServletException {
        IInst = Fabrica.getInstance().getIControladorInstituciones();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostrar el formulario de alta de institución
        request.getRequestDispatcher("/WEB-INF/altaInstitucion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String sitioWeb = request.getParameter("sitioWeb");
        String descripcion = request.getParameter("descripcion");

        // Validación de campos
        if (nombre == null || nombre.isBlank() || 
            sitioWeb == null || sitioWeb.isBlank() || 
            descripcion == null || descripcion.isBlank()) {

            request.setAttribute("error", "No puede haber campos vacíos");
            request.getRequestDispatcher("/WEB-INF/altaInstitucion.jsp").forward(request, response);
            return;
        }

        try {
            // Crear el objeto DataInstitucion (nombre, descripcion, sitioWeb)
            DataInstitucion dataInst = new DataInstitucion(nombre, descripcion, sitioWeb);
            
            // Llamar al controlador para crear la institución
            IInst.nuevaInstitucion(dataInst);

            // Redirigir al home con mensaje de éxito
            session.setAttribute("mensaje", "La Institución se ha creado con éxito");
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (InstitucionRepetidaException e) {
            request.setAttribute("error", "Ya existe una institución con ese nombre.");
            request.getRequestDispatcher("/WEB-INF/altaInstitucion.jsp").forward(request, response);

        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta la institucion");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }
}
