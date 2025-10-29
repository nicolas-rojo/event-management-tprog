package com.miseventos.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import excepciones.*;

import cliente.ws.eventos.*;
import cliente.ws.usuarios.*;
import cliente.ws.instituciones.*;
import cliente.ws.eventos.StringArray;
import cliente.ws.eventos.DataEventoCompletoArray;

import cliente.ws.eventos.DataEventoCompleto;

import excepciones.EventoNoExisteExcepcion;

//import cliente.ws.eventos.DataEventoCompleto;

//import cliente.ws.eventos.EventoNoExisteExcepcion;

@WebServlet("/home")
public class ListarEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
    
    @Override
    public void init() throws ServletException {  
    	// Acá lo que añadí
    	ControladorEventoWSService servicio = new ControladorEventoWSService();
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
        IEV_WS = servicio.getControladorEventoWSPort();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
        ICU_WS.cargarDatos();
        System.out.println("ListarEventoWS");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Obtener el parámetro de categoría si existe
			String categoriaFiltro = request.getParameter("categoria");
			
			//estas lineas son nuevas,  estoy probando
			StringArray c = IEV_WS.listarCategorias();
			List<String> categorias = c.getItem();
			
			//List<String> categorias = IEV.listarCategorias(); esto era del servlet original
			request.setAttribute("categorias", categorias);
			request.setAttribute("categoriaSeleccionada", categoriaFiltro);
			
			// Obtener eventos filtrados o todos
			List<DataEventoCompleto> listaAux;
			if (categoriaFiltro != null && !categoriaFiltro.trim().isEmpty()) {
				// Filtrar eventos por categoría
				listaAux = IEV_WS.getEventosConCategoria(categoriaFiltro).getItem();
			} else {
				// Mostrar todos los eventos
				DataEventoCompletoArray aux = IEV_WS.listarInfoEvento();
				listaAux = aux.getItem();
			}
			
			List<DataEventoCompleto> lista = new ArrayList<>();
			for (DataEventoCompleto dCom : listaAux) {
				if (!IEV_WS.eventoFinalizado(dCom.getNombre()))
					lista.add(dCom);
			}
			
			request.setAttribute("eventos", listaAux);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron listar los eventos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String eventoABorrar = request.getParameter("eventoDarBaja");
	    
	    if (eventoABorrar != null && !eventoABorrar.isEmpty()) {
	            IEV_WS.darDeBaja(eventoABorrar); 
	            request.setAttribute("mensaje", "Evento dado de baja correctamente.");

	        }
	    doGet(request, response);
	    }
	    
	
}