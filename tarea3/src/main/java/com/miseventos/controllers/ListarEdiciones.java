package com.miseventos.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.miseventos.utils.fabricaWS;

import cliente.ws.eventos.Estado;
import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.DataEdicion;
import cliente.ws.eventos.IControladorEventoWS;

@WebServlet("/listarEdiciones")
public class ListarEdiciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	    
	@Override
	public void init() throws ServletException {  
		IEV_WS = fabricaWS.getControladorEventoWS();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nombreEvento = request.getParameter("evento");
        
        List<String> nombresEdiciones = IEV_WS.listarEdiciones(nombreEvento).getItem();
        
        List<DataEdicion> ediciones = new ArrayList<>();
        
        if (nombresEdiciones != null) {
            for (String nombreEdicion : nombresEdiciones) {
                DataEdicion edicion = IEV_WS.obtenerEdicionEvento(nombreEvento, nombreEdicion);
                if (edicion != null && IEV_WS.getEstado(nombreEdicion, nombreEvento) == Estado.CONFIRMADO) {
                    ediciones.add(edicion);
                }
            }
        }
        
        request.setAttribute("evento", nombreEvento);
        request.setAttribute("ediciones", ediciones);
        request.getRequestDispatcher("/WEB-INF/listarEdiciones.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
