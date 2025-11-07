package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.miseventos.utils.fabricaWS;
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Guardo la pagina desde la que se hizo el logout para retornar a ella
		String referer = request.getHeader("referer");
		
		// Invalido la sesion
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		
		if (referer != null && !(referer.isEmpty()))
			response.sendRedirect(referer);
		else
			response.sendRedirect(request.getContextPath() + "/home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
