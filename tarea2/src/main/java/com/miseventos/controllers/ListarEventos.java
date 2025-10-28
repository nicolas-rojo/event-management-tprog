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

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

import cliente.ws.eventos.*;
import cliente.ws.usuarios.*;
import cliente.ws.instituciones.*;
import cliente.ws.eventos.StringArray;

import excepciones.EventoNoExisteExcepcion;

//import cliente.ws.eventos.DataEventoCompleto;

//import cliente.ws.eventos.EventoNoExisteExcepcion;

@WebServlet("/home")
public class ListarEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	private IUsuario ICU;
	
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
    
    @Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    	IEV = Fabrica.getInstance().getIControladorEventos();
    	ICU.cargarDatos();
    	
    	
    	// Acá lo que añadí
    	System.out.println("01");
    	ControladorEventoWSService servicio = new ControladorEventoWSService();
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
    	System.out.println("02");
        IEV_WS = servicio.getControladorEventoWSPort();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
        ICU_WS.cargarDatos();
        System.out.println("03");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Obtener el parámetro de categoría si existe
			System.out.println("05");
			String categoriaFiltro = request.getParameter("categoria");
			System.out.println("06");
			
			//estas lineas son nuevas,  estoy probando
			System.out.println("07");
			StringArray c = IEV_WS.listarCategorias();
			System.out.println("08");
			List<String> categorias = c.getItem();
			System.out.println("09");
			
			//List<String> categorias = IEV.listarCategorias(); esto era del servlet original
			request.setAttribute("categorias", categorias);
			request.setAttribute("categoriaSeleccionada", categoriaFiltro);
			
			// Obtener eventos filtrados o todos
			List<logica.datatypes.DataEventoCompleto> listaAux;
			if (categoriaFiltro != null && !categoriaFiltro.trim().isEmpty()) {
				// Filtrar eventos por categoría
				listaAux = IEV.getEventosConCategoria(categoriaFiltro);
			} else {
				// Mostrar todos los eventos
				logica.datatypes.DataEventoCompleto[] aux = IEV.listarInfoEvento();
				listaAux = new ArrayList<>(Arrays.asList(aux));
			}
			List<logica.datatypes.DataEventoCompleto> lista = new ArrayList<>();
			for (logica.datatypes.DataEventoCompleto dCom : listaAux) {
				if (!IEV.eventoFinalizado(dCom.getNombre()))
					lista.add(dCom);
			}
			
			request.setAttribute("eventos", lista);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (EventoNoExisteExcepcion e) {
			request.setAttribute("error", "Error al cargar los datos");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron listar los eventos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String eventoABorrar = request.getParameter("eventoDarBaja");
	    
	    if (eventoABorrar != null && !eventoABorrar.isEmpty()) {
	            IEV.darDeBaja(eventoABorrar); 
	            request.setAttribute("mensaje", "Evento dado de baja correctamente.");

	        }
	    doGet(request, response);
	    }
	    
	
}