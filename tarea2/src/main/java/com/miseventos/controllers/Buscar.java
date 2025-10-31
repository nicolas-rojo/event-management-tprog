package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.miseventos.utils.*;
import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/buscar")
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
    private IEventos IEV;
    
    @Override
    public void init() throws ServletException {
        ICU = Fabrica.getInstance().getIControladorUsuario();
        IEV = Fabrica.getInstance().getIControladorEventos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q"); //String de la busqueda
		String ordenar = request.getParameter("ordenar"); //Parametro de ordenamiento
		
		if (query == null)
			query = "";
		if (ordenar == null)
			ordenar = "fecha";
		
		try {
			DataEventoCompleto[] aux = IEV.listarInfoEvento();
			List<DataEventoCompleto> listaAux = new ArrayList<>(Arrays.asList(aux)); //Obtengo todos los eventos del sistema
			List<DataEventoCompleto> eventosFinal = new ArrayList<>();
			for (DataEventoCompleto dCom : listaAux) { 
				if (!IEV.eventoFinalizado(dCom.getNombre()) && cumpleCriterio(dCom, query)) //Filtro los eventos no finalizados y que cumplan el criterio del query
					eventosFinal.add(dCom);
			}
			
            List<DataEdicion> edicionesFinal = new ArrayList<>();
			for (DataEventoCompleto dEv : eventosFinal) {
	            List<String> nombresEdiciones = IEV.listarEdiciones(dEv.getNombre());
	            if (nombresEdiciones != null) {
	            	for (String nombreEdicion : nombresEdiciones) {
	                    DataEdicion edicion = IEV.obtenerEdicionEvento(dEv.getNombre(), nombreEdicion);
	                    if (IEV.getEstado(nombreEdicion, dEv.getNombre()) == Estado.Confirmado && cumpleCriterio(edicion, query))
	                        edicionesFinal.add(edicion);
	                }
	            }
			}
			
			List<Object> resultados = new ArrayList<>();
			resultados.addAll(eventosFinal);
			resultados.addAll(edicionesFinal);
			ordenarResultados(resultados, ordenar);
			
			request.setAttribute("query", query);
			request.setAttribute("ordenamiento", ordenar);
			request.setAttribute("resultados", resultados);
			request.getRequestDispatcher("/WEB-INF/resBusqueda.jsp").forward(request, response);
			
		} catch (EventoNoExisteExcepcion e) {
			request.setAttribute("error", "Error al cargar los datos");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron listar los eventos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
	
	private boolean cumpleCriterio(DataEventoCompleto evento, String query) {
		if (query == null || query.trim().isEmpty())
			return true;
		
		String nomEv = nombreUtils.normalizarNombre(evento.getNombre());
		String descEv = nombreUtils.normalizarNombre(evento.getDescripcion());
		String qNorm = nombreUtils.normalizarNombre(query);
		
		return nomEv.contains(qNorm) || descEv.contains(qNorm);
	}
	
	private boolean cumpleCriterio(DataEdicion edicion, String query) {
		if (query == null || query.trim().isEmpty())
			return true;
		
		String nomEv = nombreUtils.normalizarNombre(edicion.getNombre());
		String qNorm = nombreUtils.normalizarNombre(query);
		
		return nomEv.contains(qNorm);
	}
	
	private void ordenarResultados(List<Object> resultados, String criterio) {
		switch (criterio) {
		case "alfabetico_asc":
			Collections.sort(resultados, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					String nombre1 = getNombre(o1);
					String nombre2 = getNombre(o2);
					return nombre1.compareToIgnoreCase(nombre2);
				}
			});
			break;
			
		case "alfabetico_desc":
			Collections.sort(resultados, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					String nombre1 = getNombre(o1);
					String nombre2 = getNombre(o2);
					return nombre2.compareToIgnoreCase(nombre1);
				}
			});
			break;
			
		case "fecha":
			Collections.sort(resultados, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					LocalDate fecha1 = getFecha(o1);
					LocalDate fecha2 = getFecha(o2);
					return fecha2.compareTo(fecha1);
				}
			});
			break;
		}
	}
	
	private String getNombre(Object obj) {
		if (obj instanceof DataEventoCompleto)
			return ((DataEventoCompleto) obj).getNombre();
		else if (obj instanceof DataEdicion)
			return ((DataEdicion) obj).getNombre();
		else
			return "";
	}
	
	private LocalDate getFecha(Object obj) {
		if (obj instanceof DataEventoCompleto)
			return ((DataEventoCompleto) obj).getFechaAlta();
		else if (obj instanceof DataEdicion)
			return ((DataEdicion) obj).getFechaAlta();
		else
			return LocalDate.MIN;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
