package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
	
	@Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    	ICU.cargarDatos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickmail = request.getParameter("nickmail");
		String pass = request.getParameter("clave");
		try { 
			DataUsuario res = ICU.login(nickmail, pass);
			if (res != null) {
				String tipo = ICU.getTipoUsuario(res.getEmail());
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
		} catch (UsuarioNoExisteException e) {
			request.setAttribute("error", "Usuario o Contraseña Incorrectos");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error al intentar iniciar sesion");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
