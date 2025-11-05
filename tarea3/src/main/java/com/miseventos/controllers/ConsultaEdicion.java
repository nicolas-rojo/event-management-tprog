package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import cliente.ws.usuarios.*;
import cliente.ws.eventos.*;
import cliente.ws.eventos.DataEdicion;

import java.util.ArrayList;

@WebServlet("/consultaEdicion")
public class ConsultaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;

	@Override
	public void init() throws ServletException {
		ControladorEventoWSService servicio = new ControladorEventoWSService();
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
        IEV_WS = servicio.getControladorEventoWSPort();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		request.setAttribute("registrado", false);
		
		DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");
		String tipo = (String) session.getAttribute("tipoUsr");

		String eventoSeleccionado = request.getParameter("evento");
		String edicionSeleccionada = request.getParameter("edicion");
		if (eventoSeleccionado == null || edicionSeleccionada == null) {
			request.setAttribute("error", "Faltan parámetros de evento o edición");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		String organizador = IEV_WS.obtenerOrganizadorEdicion(eventoSeleccionado, edicionSeleccionada);
		DataEdicion dataEd = IEV_WS.obtenerEdicionEvento(eventoSeleccionado, edicionSeleccionada);
		if (dataEd == null) {
			request.setAttribute("error", "No se encontró la edición del evento");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		DataOrganizador dataOrg = null;
		try {
			dataOrg = ICU_WS.getOrganizador(organizador);
		} catch (UsuarioNoExisteException_Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Organizador no existente");
			request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron cargar los datos del organizador");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
		
		
		List<DataPatrocinioCompleto> dataPatrocinios = IEV_WS.obtenerPatrociniosEdicion(eventoSeleccionado, edicionSeleccionada).getItem();
		List<String> TRegistros = IEV_WS.obtenerTipoRegistrosEdicion(eventoSeleccionado, edicionSeleccionada).getItem();
		List<DataTRegistro> dataTRegistros = new ArrayList<>();
		if (TRegistros != null) {
			for (String TRegistro : TRegistros) {
				DataTRegistro data = IEV_WS.getDataTRegistro(eventoSeleccionado, edicionSeleccionada, TRegistro);
				if (data != null)
					dataTRegistros.add(data);
			}
		}
		
		ParEdicionRegistro registro = ICU_WS.estaRegistrado(dataU.getNickname(), edicionSeleccionada);
		if (registro != null) {
			request.setAttribute("registrado", true);
			request.setAttribute("dataRegistro", registro);
		}
		
		request.setAttribute("dataEdicion", dataEd);
		request.setAttribute("dataOrganizador", dataOrg);
		request.setAttribute("dataTRegistros", dataTRegistros);
		request.setAttribute("dataPatrocinios", dataPatrocinios);
		request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
		
	}
}
