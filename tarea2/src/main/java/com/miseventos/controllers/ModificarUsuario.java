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
import excepciones.ContrasenaIncorrectaException;

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

        // Obtener datos de contraseña
        String passActual = request.getParameter("passActual");
        String passNueva = request.getParameter("passNueva");
        String passConfirmar = request.getParameter("passConfirmar");

        try {
            // Verificar si se quiere cambiar la contraseña
            boolean cambiarPassword = false;
            if ((passNueva != null && !passNueva.trim().isEmpty()) || 
                (passConfirmar != null && !passConfirmar.trim().isEmpty())) {
                
                cambiarPassword = true;
                
                // Validar que se hayan completado todos los campos de contraseña
                if (passActual == null || passActual.trim().isEmpty()) {
                    request.setAttribute("error", "Debe ingresar su contraseña actual para cambiarla.");
                    try {
                        cargarDatosUsuario(request, email, tipoUsuario);
                    } catch (UsuarioNoExisteException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
                    return;
                }
                
                if (passNueva == null || passNueva.trim().isEmpty()) {
                    request.setAttribute("error", "Debe ingresar la nueva contraseña.");
                    try {
                        cargarDatosUsuario(request, email, tipoUsuario);
                    } catch (UsuarioNoExisteException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
                    return;
                }
                
                if (passConfirmar == null || passConfirmar.trim().isEmpty()) {
                    request.setAttribute("error", "Debe confirmar la nueva contraseña.");
                    try {
                        cargarDatosUsuario(request, email, tipoUsuario);
                    } catch (UsuarioNoExisteException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
                    return;
                }
                
                // Validar que las contraseñas nuevas coincidan
                if (!passNueva.equals(passConfirmar)) {
                    request.setAttribute("error", "Las contraseñas nuevas no coinciden.");
                    try {
                        cargarDatosUsuario(request, email, tipoUsuario);
                    } catch (UsuarioNoExisteException e) {
                        request.setAttribute("error", "El usuario no existe.");
                        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
                    return;
                }
            }

            if ("Asistente".equals(tipoUsuario)) {
                String nuevoNombre = request.getParameter("nombre");
                String nuevoApellido = request.getParameter("apellido");
                
                if (cambiarPassword) {
                    ICU.modificarAsistenteConPassword(email, nuevoNombre, nuevoApellido, passActual, passNueva);
                } else {
                    ICU.modificarAsistente(email, nuevoNombre, nuevoApellido);
                }
                
                DataAsistente asistenteActualizado = ICU.getAsistente(email);
                session.setAttribute("datosUsr", asistenteActualizado);
                response.sendRedirect(request.getContextPath() + "/detalleUsuario?email=" + email);

            } else if ("Organizador".equals(tipoUsuario)) {
                String nuevoNombre = request.getParameter("nombre");
                String nuevaDescripcion = request.getParameter("descripcion");
                String nuevaUrl = request.getParameter("url");
                
                if (cambiarPassword) {
                    ICU.modificarOrganizadorConPassword(email, nuevoNombre, nuevaDescripcion, nuevaUrl, passActual, passNueva);
                } else {
                    ICU.modificarOrganizador(email, nuevoNombre, nuevaDescripcion, nuevaUrl);
                }
                
                DataOrganizador organizadorActualizado = ICU.getOrganizador(email);
                session.setAttribute("datosUsr", organizadorActualizado);
                response.sendRedirect(request.getContextPath() + "/detalleUsuario?email=" + email);
            }
            
        } catch (ContrasenaIncorrectaException e) {
            request.setAttribute("error", "La contraseña actual es incorrecta.");
            try {
                cargarDatosUsuario(request, email, tipoUsuario);
                request.getRequestDispatcher("/WEB-INF/modificarUsuario.jsp").forward(request, response);
            } catch (UsuarioNoExisteException ex) {
                request.setAttribute("error", "El usuario no existe.");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
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
    
    private void cargarDatosUsuario(HttpServletRequest request, String email, String tipoUsuario) throws UsuarioNoExisteException {
        if ("Asistente".equals(tipoUsuario)) {
            DataAsistente asistente = ICU.getAsistente(email);
            request.setAttribute("usuario", asistente);
            request.setAttribute("tipo", "Asistente");
        } else if ("Organizador".equals(tipoUsuario)) {
            DataOrganizador organizador = ICU.getOrganizador(email);
            request.setAttribute("usuario", organizador);
            request.setAttribute("tipo", "Organizador");
        }
    }
}