package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.Estado;
import logica.interfaces.IEventos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listarEdiciones")
public class ListarEdiciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	    
	@Override
	public void init() throws ServletException {  
	    IEV = Fabrica.getInstance().getIControladorEventos();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nombreEvento = request.getParameter("evento");
        
        List<String> nombresEdiciones = IEV.listarEdiciones(nombreEvento);
        List<DataEdicion> ediciones = new ArrayList<>();
        
        if (nombresEdiciones != null) {
            for (String nombreEdicion : nombresEdiciones) {
                DataEdicion edicion = IEV.obtenerEdicionEvento(nombreEvento, nombreEdicion);
                if (edicion != null && IEV.getEstado(nombreEdicion, nombreEvento) == Estado.Confirmado) {
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
