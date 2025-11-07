package com.miseventos.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.miseventos.utils.fabricaWS;

import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.usuarios.UsuarioNoExisteException_Exception;
import cliente.ws.usuarios.DataUsuario;



@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuarioWS ICU_WS;
	
	@Override
    public void init() throws ServletException {  
        ICU_WS = fabricaWS.getControladorUsuarioWS();
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
				String tipo = ICU_WS.getTipoUsuario(res.getEmail());
				if ("Organizador".equals(tipo)) {
					request.setAttribute("error", "Uso exclusivo para Asistentes");
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				} else if ("Asistente".equals(tipo)) {
					HttpSession session = request.getSession();
					session.setAttribute("datosUsr", res);
					session.setAttribute("tipoUsr", "asistente");
					response.sendRedirect(request.getContextPath() + "/home");
				} else {
					request.setAttribute("error", "Error Desconocido");
					request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Usuario o Contraseña Incorrectos");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		} catch (UsuarioNoExisteException_Exception e) {
			request.setAttribute("error", "Usuario o Contraseña Incorrectos");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error al intentar iniciar sesion");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
