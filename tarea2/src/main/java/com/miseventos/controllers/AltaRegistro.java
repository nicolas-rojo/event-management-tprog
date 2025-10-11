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

@WebServlet("/altaRegistro")
@MultipartConfig

public class AltaRegistro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
	private IEventos IEV;
	
	@Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    	IEV = Fabrica.getInstance().getIControladorEventos();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String edicion = request.getParameter("edicion");
		String evento = request.getParameter("evento");
		String treg = request.getParameter("treg");
		DataTRegistro dataTR = IEV.getDataTRegistro(evento, edicion, treg);
		System.out.println(dataTR.getNombre() + " " + dataTR.getDescr());
		request.setAttribute("dataTR", dataTR);		
		request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
		String asistente = datosU.getNickname();		
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String tiporegistro= request.getParameter("treg");
		LocalDate fecha = LocalDate.now();		
		
		try {
			ICU.nuevoRegistro(asistente, evento, edicion, tiporegistro, fecha);
			response.sendRedirect(request.getContextPath() + "/home");
		}catch(AsistenteYaRegistrado e) {
			request.setAttribute("errorYaRegistrado", "Ya estas registrado a esta edicion");
			request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
		}catch(NoHayCupoEdicionTRegistro e) {
			request.setAttribute("errorCupo", "No hay cupos disponibles para esta edicion");
			request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta el registro");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
		
	
}