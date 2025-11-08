package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;

import com.miseventos.utils.nombreUtils;

import java.util.ArrayList;
import java.util.Arrays;

import com.miseventos.utils.fabricaWS;

import cliente.ws.eventos.*;
import cliente.ws.instituciones.ControladorInstitucionesWSService;
import cliente.ws.usuarios.ControladorUsuarioWSService;

@WebServlet("/altaEvento")
@MultipartConfig
public class AltaEvento extends HttpServlet {
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
        System.out.println("AltaEventoWS");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/altaEvento.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombreEv");
        String descripcion = request.getParameter("desc");
        String sigla = request.getParameter("sigla");
        LocalDate fechaActual = LocalDate.now();
        
        // SOLUCIÓN: Manejar el caso null
        String[] aux = request.getParameterValues("categorias[]");
        List<String> cats = new ArrayList<>();
        
        if (aux != null) {
            cats = Arrays.asList(aux);
        }
        
        // Validaciones básicas
        if (nombre == null || nombre.trim().isEmpty() ||
            descripcion == null || descripcion.trim().isEmpty() ||
            sigla == null || sigla.trim().isEmpty()) {
            
            request.setAttribute("error", "Todos los campos son requeridos");
            
            request.setAttribute("nombreEv", nombre);
            request.setAttribute("desc", descripcion);
            request.setAttribute("sigla", sigla);
            request.setAttribute("categoriasSeleccionadas", cats);
            
            request.getRequestDispatcher("/WEB-INF/altaEvento.jsp").forward(request, response);
            return;
        }
        
        DataEvento data = new DataEvento();
        data.setNombre(nombre.trim());
        data.setSigla(sigla.trim());
        data.setFechaAlta(fechaActual.toString()); //Añadí esto...................
        //data.setFechaAlta(fechaActual);
        data.setDescripcion(descripcion.trim());
        
       
        try {
        	StringArray categorias = new StringArray();
        	categorias.getItem().addAll(cats);
            IEV_WS.nuevoEvento(data, categorias);
            cargarImg(request, nombre);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch(EventoRepetidoExcepcion_Exception | EventoSinCategoriaExcepcion_Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            if (e instanceof EventoRepetidoExcepcion_Exception) {
                request.setAttribute("nombreEv", "");
            } else {
                request.setAttribute("nombreEv", request.getParameter("nombreEv"));
            }
            
            request.setAttribute("desc", descripcion);
            request.setAttribute("sigla", sigla);
            request.setAttribute("categoriasSeleccionadas", cats);
            
            request.getRequestDispatcher("/WEB-INF/altaEvento.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta el evento");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }
    
    private void cargarImg(HttpServletRequest request, String nick) throws IOException, ServletException {
		Part filePart = request.getPart("imagen");
		if (filePart == null || filePart.getSize() == 0) {
			return; //NO SE SUBIO NINGUNA IMAGEN
		}
		
		try {
			BufferedImage imagen = ImageIO.read(filePart.getInputStream());
			String nomNorm = "EV-" + nombreUtils.normalizarNombre(nick);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(imagen, "png", baos);
	        byte[] imageBytes = baos.toByteArray();
	        baos.close();
	        
	        IEV_WS.uploadFile(nomNorm, imageBytes);
		} catch (Exception e) {}	
	}
}