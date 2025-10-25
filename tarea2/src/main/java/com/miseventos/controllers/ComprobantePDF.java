package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.*;
import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;

@WebServlet("/ComprobantePDF")
public class ComprobantePDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	private IUsuario ICU;
	
	
	@Override
	public void init() throws ServletException {
		IEV = Fabrica.getInstance().getIControladorEventos();
		ICU = Fabrica.getInstance().getIControladorUsuario();
	}
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Decirle al navegador que viene un PDF
		response.setContentType("application/pdf");
		
		// Abrir en otra pestaña (inline) y ponerle nombre al archivo
		response.setHeader("Content-Disposition", "inline; filename=\"comprobante.pdf\"");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
	    String asistente = datosU.getNombre();
	    String apellido = null;
	    try {
	    	apellido = ICU.getAsistente(datosU.getEmail()).getApellido();
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		}
		String evento = request.getParameter("evento");
		String edicion = request.getParameter("edicion");
		String ciudad = request.getParameter("ciudad");
		String fecha = request.getParameter("fecha");
		
		System.out.println(asistente);
		System.out.println(apellido);
		System.out.println(evento);
		System.out.println(edicion);
		System.out.println(ciudad);
		System.out.println(fecha);
		

//	    try {
//	        OutputStream out = response.getOutputStream();
//	        Document document = new Document();
//	        PdfWriter.getInstance(document, out);
//	        document.open();
//
//	        document.add(new Paragraph("Comprobante de Asistencia"));
//	        document.add(new Paragraph("Usuario: "));
//
//	        document.close();
//	        out.close();
//	    } catch (DocumentException e) {
//	        e.printStackTrace();
//	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
