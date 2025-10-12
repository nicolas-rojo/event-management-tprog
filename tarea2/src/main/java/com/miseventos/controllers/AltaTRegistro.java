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

@WebServlet("/altaTReg")
@MultipartConfig
public class AltaTRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	
	@Override
	public void init() throws ServletException {  
    	IEV = Fabrica.getInstance().getIControladorEventos();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/altaTRegistro.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html;charset=UTF-8");
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String costotr = request.getParameter("costo");
		String cupotr = request.getParameter("cupo");
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		

		
		try {
			IEV.nuevoTipoRegistro(new DataTRegistro(nombre, descripcion,Float.parseFloat(costotr), Integer.parseInt(cupotr)), evento, edicion);
			response.sendRedirect(request.getContextPath() + "/home");
		}catch(TipoDeRegistroRepetidoException e) {
			request.setAttribute("errorExiste", "Ya existe un tipo de registro con este nombre");
			request.getRequestDispatcher("/WEB-INF/altaTRegistro.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta el tipo de registro");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}