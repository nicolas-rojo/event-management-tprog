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
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickmail = request.getParameter("nickmail");
		String pass = request.getParameter("clave");
		ResLogin res = ICU.login(nickmail, pass);
		
		switch(res) {
			case asistente: {
				HttpSession session = request.getSession();
				session.setAttribute("tipoUsr", "asistente");
				session.setAttribute("nickmail", nickmail);
				response.sendRedirect(request.getContextPath() + "/home");
				break;				
			}
			case organizador: {
				HttpSession session = request.getSession();
				session.setAttribute("tipoUsr", "organizador");
				session.setAttribute("nickmail", nickmail);
				response.sendRedirect(request.getContextPath() + "/home");
				break;
			}
			case error: 
			default: {
				request.setAttribute("error", "Usuario o Contraseña Incorrectos");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				break;
			}
		}
	}
}
