package com.miseventos.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.DataAsistente;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.usuarios.ParEdicionRegistro;
import cliente.ws.usuarios.UsuarioNoExisteException_Exception;

import cliente.ws.usuarios.DataUsuario;

@WebServlet("/detalleUsuario")
public class DetalleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
	
    @Override
    public void init() throws ServletException {
    	ControladorEventoWSService servicio = new ControladorEventoWSService();
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
        IEV_WS = servicio.getControladorEventoWSPort();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DataUsuario usr = (DataUsuario) session.getAttribute("datosUsr");
		try {
			DataAsistente asistente = ICU_WS.getAsistente(usr.getEmail());			
			request.setAttribute("asistente", asistente);
		} catch (UsuarioNoExisteException_Exception e) {
            request.setAttribute("error", "El usuario solicitado no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo obtener la informacion del usuario");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
		List<ParEdicionRegistro> registros = ICU_WS.getRegistrosAsistente(usr.getNickname()).getItem();
        request.setAttribute("registros", registros);
        
        request.getRequestDispatcher("/WEB-INF/detalleUsuario.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreEdicion = request.getParameter("nombreEdicion");
		String nickname = request.getParameter("nickname");
		ICU_WS.setAsistencia(nombreEdicion, nickname);
        response.sendRedirect(request.getContextPath() + "/detalleUsuario");
	}

}
