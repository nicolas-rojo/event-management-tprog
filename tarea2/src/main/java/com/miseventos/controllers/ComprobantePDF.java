package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.DataUsuario;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.usuarios.UsuarioNoExisteException_Exception;


@WebServlet("/ComprobantePDF")
public class ComprobantePDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
	
	
	@Override
	public void init() throws ServletException {
		ControladorEventoWSService servicio = new ControladorEventoWSService();
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
        IEV_WS = servicio.getControladorEventoWSPort();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
    	System.out.println("RegisterWS");
	}
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Configurar response PARA PDF
	    response.setContentType("application/pdf");
	    
	    HttpSession session = request.getSession();
	    DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
	    String asistente = datosU.getNombre();
	    String apellido = null;
	    String evento = request.getParameter("evento");
	    String edicion = request.getParameter("edicion");
	    String ciudad = request.getParameter("ciudad");
	    String fecha = request.getParameter("fecha");
	    
	    response.setHeader("Content-Disposition", "attachment; filename=\"Comprobante_Asistencia_"+ edicion +".pdf\"");
	    
	    try {
	        apellido = ICU_WS.getAsistente(datosU.getEmail()).getApellido();
	    } catch (UsuarioNoExisteException_Exception e) {
	        e.printStackTrace();
	    }
	    

	    try {

	        Document document = new Document();
	        PdfWriter.getInstance(document, response.getOutputStream());
	        
	        document.open();
	        
	        document.add(new Paragraph("COMPROBANTE DE ASISTENCIA"));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Asistente: " + asistente + " " + (apellido != null ? apellido : "")));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Evento: " + evento));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Fecha del Registro: " + fecha));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Fecha de emisión: " + new java.util.Date()));
	        document.add(Chunk.NEWLINE);
	        document.add(Chunk.NEWLINE);
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Se deja constancia de que " + asistente +" "+ apellido + " asistio a la edicion " + edicion + " en la ciudad de " + ciudad));
	        Image img = Image.getInstance("C:/Users/facun/Desktop/logotransparente.png");
            img.scaleToFit(400, 400);
            float x = (document.getPageSize().getWidth() - img.getScaledWidth()) / 2;
            float y = (document.getPageSize().getHeight() - img.getScaledHeight()) / 2;
            img.setAbsolutePosition(x, y);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
	        
	        document.close();
	        
	    } catch (DocumentException e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generando PDF");
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
