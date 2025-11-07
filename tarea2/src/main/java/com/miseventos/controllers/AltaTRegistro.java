package com.miseventos.controllers;
//ARREGLADO

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import com.miseventos.utils.fabricaWS;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.eventos.DataTRegistro;

import cliente.ws.eventos.TipoDeRegistroRepetidoException_Exception;


@WebServlet("/altaTReg")
@MultipartConfig
public class AltaTRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	
	@Override
	public void init() throws ServletException {  
		IEV_WS = fabricaWS.getControladorEventoWS();
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
			DataTRegistro d = new DataTRegistro();
			d.setCosto(Float.parseFloat(costotr));
			d.setCupo(Integer.parseInt(cupotr));
			d.setDescripcion(descripcion);
			d.setNombre(nombre);
			IEV_WS.nuevoTipoRegistro(d, evento, edicion);
			response.sendRedirect(request.getContextPath() + "/home");
		}catch(TipoDeRegistroRepetidoException_Exception e) {
			request.setAttribute("errorExiste", "Ya existe un tipo de registro con este nombre");
            request.setAttribute("descripcion", descripcion);
            request.setAttribute("costo", costotr);
            request.setAttribute("cupo", cupotr);
			request.getRequestDispatcher("/WEB-INF/altaTRegistro.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta el tipo de registro");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}