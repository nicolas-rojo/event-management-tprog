package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.IUsuario;
import logica.datatypes.*;
import excepciones.UsuarioNoExisteException;

@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUsuario ICU;

    @Override
    public void init() throws ServletException {
        ICU = Fabrica.getInstance().getIControladorUsuario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");
        String email = dataU.getEmail();
        try {
            String tipoUsuario = ICU.getTipoUsuario(email);
            
            if ("Asistente".equals(tipoUsuario)) {
                DataAsistente asistente = ICU.getAsistente(email);
                request.setAttribute("usuario", asistente);
                request.setAttribute("tipo", "Asistente");
                
            } else if ("Organizador".equals(tipoUsuario)) {
                DataOrganizador organizador = ICU.getOrganizador(email);
                request.setAttribute("usuario", organizador);
                request.setAttribute("tipo", "Organizador");
            }
            
            request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
                   
        } catch (UsuarioNoExisteException e) {
            request.setAttribute("error", "El usuario solicitado no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "No se pudo cargar la información del usuario");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");
        String email = dataU.getEmail();
        String tipoUsuario = request.getParameter("tipoUsuario");

        try {
            if ("Asistente".equals(tipoUsuario)) {
                String nuevoNombre = request.getParameter("nombre");
                String nuevoApellido = request.getParameter("apellido");
                ICU.modificarAsistente(email, nuevoNombre, nuevoApellido);
                DataAsistente asistenteActualizado = ICU.getAsistente(email);
                session.setAttribute("datosUsr", asistenteActualizado);
                response.sendRedirect(request.getContextPath() + "/detalleUsuario?email=" + email);

            } else if ("Organizador".equals(tipoUsuario)) {
                String nuevoNombre = request.getParameter("nombre");
                String nuevaDescripcion = request.getParameter("descripcion");
                String nuevaUrl = request.getParameter("url");
                ICU.modificarOrganizador(email, nuevoNombre, nuevaDescripcion, nuevaUrl);
                DataOrganizador organizadorActualizado = ICU.getOrganizador(email);
                session.setAttribute("datosUsr", organizadorActualizado);
                response.sendRedirect(request.getContextPath() + "/detalleUsuario?email=" + email);
            }
        } catch (UsuarioNoExisteException e) {
            request.setAttribute("error", "El usuario no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "No se pudo modificar el usuario");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }
}