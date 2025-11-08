package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL; 
import java.net.MalformedURLException;
import java.util.Properties;
import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import com.miseventos.utils.fabricaWS;
@WebServlet("/imagenes")
public class Imagenes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;

	@Override
    public void init() throws ServletException {  
		String ev = fabricaWS.getURLControladorEvento();
    	try{
    		ControladorEventoWSService servicio = new ControladorEventoWSService(new URL(ev));
    		IEV_WS = servicio.getControladorEventoWSPort();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		byte[] img = null;
		try {
			img = IEV_WS.getFile(id);
			response.setContentType("image/png");
			response.setContentLength((int) img.length);
			OutputStream out = response.getOutputStream();
			out.write(img);
			out.close();
		} catch (Exception e) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
