package com.miseventos.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.miseventos.utils.fabricaWS;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.DataEventoCompleto;
import cliente.ws.eventos.DataEventoCompletoArray;
import cliente.ws.eventos.EventoNoExisteExcepcion_Exception;
import cliente.ws.eventos.IControladorEventoWS;

@WebServlet("/home")
public class ListarEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
       
	@Override
    public void init() throws ServletException {  
		IEV_WS = fabricaWS.getControladorEventoWS();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			// Obtener eventos filtrados o todos
			DataEventoCompletoArray aux1 = IEV_WS.listarInfoEvento();
			DataEventoCompleto[] aux = aux1.getItem().toArray(new DataEventoCompleto[0]);
			List<DataEventoCompleto> listaAux = new ArrayList<>(Arrays.asList(aux));
			List<DataEventoCompleto> lista = new ArrayList<>();
			for (DataEventoCompleto dCom : listaAux) {
				if (!IEV_WS.eventoFinalizado(dCom.getNombre()))
					lista.add(dCom);
			}
			request.setAttribute("eventos", lista);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (EventoNoExisteExcepcion_Exception e) {
			request.setAttribute("error", "Error al cargar los datos");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron listar los eventos");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
