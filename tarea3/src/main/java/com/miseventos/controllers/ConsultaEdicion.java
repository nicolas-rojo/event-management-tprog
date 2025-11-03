package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/consultaEdicion")
public class ConsultaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;
	private IUsuario ICU;

	@Override
	public void init() throws ServletException {
		IEV = Fabrica.getInstance().getIControladorEventos();
		ICU = Fabrica.getInstance().getIControladorUsuario();
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
		
		String organizador = IEV.obtenerOrganizadorEdicion(eventoSeleccionado, edicionSeleccionada);
		DataEdicion dataEd = IEV.obtenerEdicionEvento(eventoSeleccionado, edicionSeleccionada);
		if (dataEd == null) {
			request.setAttribute("error", "No se encontró la edición del evento");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		
		DataOrganizador dataOrg = null;
		try {
			dataOrg = ICU.getOrganizador(organizador);
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
			request.setAttribute("error", "Organizador no existente");
			request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron cargar los datos del organizador");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
		
		
		List<DataPatrocinioCompleto> dataPatrocinios = IEV.obtenerPatrociniosEdicion(eventoSeleccionado, edicionSeleccionada);
		List<String> TRegistros = IEV.obtenerTipoRegistrosEdicion(eventoSeleccionado, edicionSeleccionada);
		List<DataTRegistro> dataTRegistros = new ArrayList<>();
		if (TRegistros != null) {
			for (String TRegistro : TRegistros) {
				DataTRegistro data = IEV.getDataTRegistro(eventoSeleccionado, edicionSeleccionada, TRegistro);
				if (data != null)
					dataTRegistros.add(data);
			}
		}
		
		ParEdicionRegistro registro = ICU.estaRegistrado(dataU.getNickname(), edicionSeleccionada);
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
