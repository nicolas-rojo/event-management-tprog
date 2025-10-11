package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;

import com.miseventos.utils.nombreUtils;

import java.util.ArrayList;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;
import java.util.Arrays;

@WebServlet("/altaEvento")
@MultipartConfig
public class AltaEvento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEventos IEV;
    
    @Override
    public void init() throws ServletException {  
        IEV = Fabrica.getInstance().getIControladorEventos();
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
            request.getRequestDispatcher("/WEB-INF/altaEvento.jsp").forward(request, response);
            return;
        }
        
        DataEvento data = new DataEvento(nombre.trim(), sigla.trim(), fechaActual, descripcion.trim());
       
        try {
            IEV.nuevoEvento(data, cats);
            cargarImg(request, nombre);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch(EventoRepetidoExcepcion | EventoSinCategoriaExcepcion e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/altaEvento.jsp").forward(request, response);
        }
    }
    
    private void cargarImg(HttpServletRequest request, String nick) throws IOException, ServletException {
		Part filePart = request.getPart("imagen");
		if (filePart == null || filePart.getSize() == 0) {
			return; //NO SE SUBIO NINGUNA IMAGEN
		}
		
		String nomNorm = nombreUtils.normalizarNombre(nick);
		
		BufferedImage imagen = ImageIO.read(filePart.getInputStream());
		String rutaRel = "/resources/images/EV-" + nomNorm + ".png";
		String rutaAbs = getServletContext().getRealPath(rutaRel);
		
		File archivoDest = new File(rutaAbs);
		ImageIO.write(imagen, "png", archivoDest);		
	}
}