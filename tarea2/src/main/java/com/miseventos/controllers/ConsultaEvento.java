package com.miseventos.controllers;

//ARREGLADO

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cliente.ws.eventos.*;
import cliente.ws.eventos.DataEventoCompleto;
import cliente.ws.eventos.Estado;

@WebServlet("/consultaEvento")
public class ConsultaEvento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorEventoWS IEV_WS;
    
    @Override
    public void init() throws ServletException {  
        
        ControladorEventoWSService servicio = new ControladorEventoWSService();
        IEV_WS = servicio.getControladorEventoWSPort();
        System.out.println("ConsultaEventoWS");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nombreEvento = request.getParameter("evento");
        try {
            DataEventoCompletoArray aux = IEV_WS.listarInfoEvento();
            List<DataEventoCompleto> listaAux;
            listaAux = aux.getItem();
            DataEventoCompleto eventoSeleccionado = null;
            
            for (DataEventoCompleto evento : listaAux) {
                if (evento.getNombre().equals(nombreEvento)) {
                    eventoSeleccionado = evento;
                    break;
                }
            }
            StringArray auxLista = IEV_WS.listarEdiciones(nombreEvento);
            List<String> nombresEdiciones = auxLista.getItem();
            List<DataEdicion> ediciones = new ArrayList<>();
            
            if (nombresEdiciones != null) {
                for (String nombreEdicion : nombresEdiciones) {
                    DataEdicion edicion = IEV_WS.obtenerEdicionEvento(nombreEvento, nombreEdicion);
                    if (edicion != null && IEV_WS.getEstado(nombreEdicion, nombreEvento) ==Estado.CONFIRMADO) {
                        ediciones.add(edicion);
                    }
                }
            }
            
            request.setAttribute("evento", eventoSeleccionado);
            request.setAttribute("ediciones", ediciones);
            request.getRequestDispatcher("/WEB-INF/consultaEvento.jsp").forward(request, response);

            
        } catch (EventoNoExisteExcepcion_Exception e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo obtener la informacion de el evento");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}