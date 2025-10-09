package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;
import java.util.Arrays;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		String eventoSeleccionado = request.getParameter("evento");
		String edicionSeleccionada = request.getParameter("edicionSeleccionada");
		String tipo = request.getParameter("tipoUsr");
		String nickmail = request.getParameter("nickmail");

		if (eventoSeleccionado == null || edicionSeleccionada == null) {
			request.setAttribute("error", "Faltan parámetros de evento o edición");
			request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
			return;
		}

		String organizador = IEV.obtenerOrganizadorEdicion(eventoSeleccionado, edicionSeleccionada);
		DataEdicion dataEd = IEV.getDataEdicion(eventoSeleccionado, edicionSeleccionada);

		if (dataEd == null) {
			request.setAttribute("error", "No se encontró la edición del evento");
			request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
			return;
		}

		DataOrganizador dataOrg = null;
		try {
			dataOrg = ICU.getOrganizador(organizador);
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
			request.setAttribute("error", "organizador no existente");
			request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
			return;
		}

		List<DataPatrocinioCompleto> dataPatrocinios = IEV.obtenerPatrociniosEdicion(eventoSeleccionado,
				edicionSeleccionada);

		List<String> TRegistros = IEV.obtenerTipoRegistrosEdicion(edicionSeleccionada, organizador);
		List<DataTRegistro> dataTRegistros = new ArrayList<>();
		if (TRegistros != null) {
			for (String TRegistro : TRegistros) {
				DataTRegistro data = IEV.getDataTRegistro(eventoSeleccionado, edicionSeleccionada, TRegistro);
				if (data != null)
					dataTRegistros.add(data);
			}
		}

		if (tipo == null) {
			response.sendRedirect(request.getContextPath() + "/consultarEdicion.jsp");
			return;
		} else {
			if ("asistente".equals(tipo)) {
				ParEdicionRegistro registro = ICU.estaRegistrado(nickmail, edicionSeleccionada);
				if (registro != null) {
					request.setAttribute("registrado", true);
					request.setAttribute("dataRegistro", registro);
				}
			} else if ("organizador".equals(tipo)) {
				if (dataOrg.getNickname().equals(IEV.obtenerOrganizadorEdicion(eventoSeleccionado, edicionSeleccionada))) {
					request.setAttribute("organizaEdicion", true);
					List<String > dataRegistros = IEV.obtenerRegistrosEdicion(eventoSeleccionado, edicionSeleccionada);
					request.setAttribute("dataRegistros", dataRegistros);
					
				}
			}
		}

		request.setAttribute("dataEdicion", dataEd);
		request.setAttribute("dataOrganizador", dataOrg);
		request.setAttribute("dataTRegistros", dataTRegistros);
		request.setAttribute("dataPatrocinios", dataPatrocinios);
		request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
	}
}
	
