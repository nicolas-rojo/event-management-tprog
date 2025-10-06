package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.time.LocalDate;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
	
	@Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String tipo = request.getParameter("tipo");
		String nombre = request.getParameter("nombre");
		String nick = request.getParameter("nick");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		try {
			HttpSession session = request.getSession();
			if ("organizador".equals(tipo)) {
                String url = request.getParameter("url");
                String desc = request.getParameter("desc");
				ICU.registrarOrganizador(new DataOrganizador(nombre, nick, mail, pass, desc, url));
				session.setAttribute("tipoUsr", "organizador");
			} else if ("asistente".equals(tipo)) {
				String apellido = request.getParameter("apellido");
				LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
				ICU.registrarAsistente(new DataAsistente(nombre, nick, mail, pass, apellido, fechaNac));
				session.setAttribute("tipoUsr", "asistente");
			} else {
				System.out.println(nombre + nick);
				request.setAttribute("error", "Error Desconocido");
				request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
				return;
			}
			session.setAttribute("nickmail", nick);
			response.sendRedirect(request.getContextPath() + "/home");
		} catch (UsuarioRepetidoException e) {
			request.setAttribute("error", "Mail o Nickname en uso");
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error Desconocido");
	        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
	}
}
