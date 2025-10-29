package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.usuarios.DataUsuario;
import excepciones.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuarioWS ICU_WS;
	
	@Override
    public void init() throws ServletException {  
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
    	ICU_WS = servicio2.getControladorUsuarioWSPort();
    	System.out.println("LoginWS");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickmail = request.getParameter("nickmail");
		String pass = request.getParameter("clave");
		try { 
			DataUsuario res = ICU_WS.login(nickmail, pass);
			if (res != null) {
				HttpSession session = request.getSession();
				session.setAttribute("datosUsr", res);
				String tipo = ICU_WS.getTipoUsuario(res.getEmail());
				switch(tipo) {
					case "Asistente": {
						session.setAttribute("tipoUsr", "asistente");
						response.sendRedirect(request.getContextPath() + "/home");
						break;	
					}
					case "Organizador": {
						session.setAttribute("tipoUsr", "organizador");
						response.sendRedirect(request.getContextPath() + "/home");
						break;
					}
					default: {
						request.setAttribute("error", "Error Desconocido");
						request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
						break;
					}
				}
			} else {
				request.setAttribute("error", "Usuario o Contraseña Incorrectos");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		//} catch (UsuarioNoExisteException e) {
		//	request.setAttribute("error", "Usuario o Contraseña Incorrectos");
		//	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error al intentar iniciar sesion");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
