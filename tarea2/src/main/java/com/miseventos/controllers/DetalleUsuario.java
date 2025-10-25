package com.miseventos.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/detalleUsuario")
public class DetalleUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUsuario ICU;
    private IEventos IEV;
    
    @Override
    public void init() throws ServletException {
        ICU = Fabrica.getInstance().getIControladorUsuario();
        IEV = Fabrica.getInstance().getIControladorEventos();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        if (email == null || email.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/consultaUsuario");
            return;
        }
        
        try {
            String tipoUsuario = ICU.getTipoUsuario(email);
            
            if ("Asistente".equals(tipoUsuario)) {
                DataAsistente asistente = ICU.getAsistente(email);
                request.setAttribute("usuario", asistente);
                request.setAttribute("tipo", "Asistente");
                
                // Cargar registros del asistente
                List<ParEdicionRegistro> registros = ICU.getRegistrosAsistente(asistente.getNickname());
                request.setAttribute("registros", registros);
                
            } else if ("Organizador".equals(tipoUsuario)) {
                DataOrganizador organizador = ICU.getOrganizador(email);
                request.setAttribute("usuario", organizador);
                request.setAttribute("tipo", "Organizador");
                
                // Cargar ediciones del organizador
                DataEdicionWeb[] ediciones = IEV.getEdicionesEventoOrganizadorWeb(organizador.getNickname());
                request.setAttribute("ediciones", ediciones);
            }
            List<String> seguidos = ICU.getSeguidos(email);
            List<String> seguidores = ICU.getSeguidores(email);
            request.setAttribute("seguidos", seguidos);
            request.setAttribute("seguidores", seguidores);
    
            request.getRequestDispatcher("/WEB-INF/detalleUsuario.jsp").forward(request, response);
                   
        } catch (UsuarioNoExisteException e) {
            request.setAttribute("error", "El usuario solicitado no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo obtener la informacion del usuario");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
