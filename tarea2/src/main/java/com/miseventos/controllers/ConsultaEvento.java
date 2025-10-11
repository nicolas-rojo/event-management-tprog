package com.miseventos.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/consultaEvento")
public class ConsultaEvento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEventos IEV;
    
    @Override
    public void init() throws ServletException {  
        IEV = Fabrica.getInstance().getIControladorEventos();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nombreEvento = request.getParameter("evento");
        try {
            DataEventoCompleto[] todosEventos = IEV.listarInfoEvento();
            DataEventoCompleto eventoSeleccionado = null;
            
            for (DataEventoCompleto evento : todosEventos) {
                if (evento.getNombre().equals(nombreEvento)) {
                    eventoSeleccionado = evento;
                    break;
                }
            }
            
            List<String> nombresEdiciones = IEV.listarEdiciones(nombreEvento);
            List<DataEdicion> ediciones = new ArrayList<>();
            
            if (nombresEdiciones != null) {
                for (String nombreEdicion : nombresEdiciones) {
                    DataEdicion edicion = IEV.getDataEdicion(nombreEvento, nombreEdicion);
                    if (edicion != null && IEV.getEstado(nombreEdicion, nombreEvento) ==Estado.Confirmado) {
                        ediciones.add(edicion);
                    }
                }
            }
            
            request.setAttribute("evento", eventoSeleccionado);
            request.setAttribute("ediciones", ediciones);
            request.getRequestDispatcher("/WEB-INF/consultaEvento.jsp").forward(request, response);

            
        } catch (EventoNoExisteExcepcion e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}