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

@WebServlet("/altaregistro")
@MultipartConfig

public class AltaRegistro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
	
	@Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/altaReg.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String asistente = request.getParameter("asistente");
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String tiporegistro= request.getParameter("tipoReg");
		LocalDate fecha = LocalDate.now();		
		
		//System.out.println("Ya registrado");
		try {
			ICU.nuevoRegistro(asistente, evento, edicion, tiporegistro, fecha);
			response.sendRedirect(request.getContextPath() + "/home");
		}catch(AsistenteYaRegistrado e) {
			request.setAttribute("errorYaRegistrado", "Ya estas registrado a esta edicion");
			request.getRequestDispatcher("/WEB-INF/altaReg.jsp").forward(request, response);
		}catch(NoHayCupoEdicionTRegistro e) {
			request.setAttribute("errorCupo", "No hay cupos disponibles para esta edicion");
			request.getRequestDispatcher("/WEB-INF/altaReg.jsp").forward(request, response);
		}
	}
		
	
}