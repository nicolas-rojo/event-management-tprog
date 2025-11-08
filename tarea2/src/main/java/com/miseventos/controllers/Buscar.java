package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.miseventos.utils.fabricaWS;

import com.miseventos.utils.*;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.DataEdicion;
import cliente.ws.eventos.DataEventoCompleto;
import cliente.ws.eventos.DataEventoCompletoArray;
import cliente.ws.eventos.Estado;
import cliente.ws.eventos.EventoNoExisteExcepcion;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.instituciones.ControladorInstitucionesWSService;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.eventos.EventoNoExisteExcepcion_Exception;


@WebServlet("/buscar")
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
    
    @Override
    public void init() throws ServletException {
    	String ev = fabricaWS.getURLControladorEvento();
    	String usr = fabricaWS.getURLControladorUsuario();
    	try{
    		ControladorEventoWSService servicio = new ControladorEventoWSService(new URL(ev));
    		IEV_WS = servicio.getControladorEventoWSPort();
        	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService(new URL(usr));
            ICU_WS = servicio2.getControladorUsuarioWSPort();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
        System.out.println("ConsultaEventoWS");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q"); //String de la busqueda
		String ordenar = request.getParameter("ordenar"); //Parametro de ordenamiento
		
		if (query == null)
			query = "";
		if (ordenar == null)
			ordenar = "fecha";
		
		try {
			
			DataEventoCompletoArray auxA = IEV_WS.listarInfoEvento();
			DataEventoCompleto[] aux = auxA.getItem().toArray(new DataEventoCompleto[0]);
			List<DataEventoCompleto> listaAux = new ArrayList<>(Arrays.asList(aux)); //Obtengo todos los eventos del sistema
			List<DataEventoCompleto> eventosFinal = new ArrayList<>();
			for (DataEventoCompleto dCom : listaAux) { 
				if (!IEV_WS.eventoFinalizado(dCom.getNombre()) && cumpleCriterio(dCom, query)) //Filtro los eventos no finalizados y que cumplan el criterio del query
					eventosFinal.add(dCom);
			}
			
            List<DataEdicion> edicionesFinal = new ArrayList<>();
			for (DataEventoCompleto dEv : eventosFinal) {
	            List<String> nombresEdiciones = IEV_WS.listarEdiciones(dEv.getNombre()).getItem();
	            if (nombresEdiciones != null) {
	            	for (String nombreEdicion : nombresEdiciones) {
	                    DataEdicion edicion = IEV_WS.obtenerEdicionEvento(dEv.getNombre(), nombreEdicion);
	                    if (IEV_WS.getEstado(nombreEdicion, dEv.getNombre()) == Estado.CONFIRMADO && cumpleCriterio(edicion, query))
	                        edicionesFinal.add(edicion);
	                }
	            }
			}
			
			List<Object> resultados = new ArrayList<>();
			resultados.addAll(eventosFinal);
			resultados.addAll(edicionesFinal);
			ordenarResultados(resultados, ordenar);
			
//			for (Object o : resultados) {
//				if (o instanceof DataEventoCompleto)
//					System.out.println(((DataEventoCompleto) o).getNombre());
//				else
//					System.out.println(((DataEdicion) o).getNombre());
//			}
			
			request.setAttribute("query", query);
			request.setAttribute("ordenamiento", ordenar);
			request.setAttribute("resultados", resultados);
			request.getRequestDispatcher("/WEB-INF/resBusqueda.jsp").forward(request, response);
			
		} catch (EventoNoExisteExcepcion_Exception e) {
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
	    String fechaStr = null;
	    
	    if (obj instanceof DataEventoCompleto) {
	        fechaStr = ((DataEventoCompleto) obj).getFechaAlta();
	    } else if (obj instanceof DataEdicion) {
	        fechaStr = ((DataEdicion) obj).getFechaAlta();
	    }
	    
	    return fechaStr != null ? LocalDate.parse(fechaStr) : LocalDate.MIN;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
