package com.miseventos.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import logica.Fabrica;
import logica.interfaces.IEventos;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataUsuario;
import excepciones.EdicionRepetidaExcepcion;

@WebServlet("/AltaEdicion")
public class AltaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;

	@Override
	public void init() throws ServletException {
		IEV = Fabrica.getInstance().getIControladorEventos();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String evento = request.getParameter("evento");

		if (evento != null) {
			evento = URLDecoder.decode(evento, StandardCharsets.UTF_8);

			request.setAttribute("evento", evento);

			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String evento = request.getParameter("evento");
		if (evento != null)
			evento = URLDecoder.decode(evento, StandardCharsets.UTF_8);

		String nombre = request.getParameter("nombreEd");
		String sigla = request.getParameter("sigla");
		String ciudad = request.getParameter("ciudad");
		String pais = request.getParameter("pais");
		String fechaIniStr = request.getParameter("fechaIni");
		String fechaFinStr = request.getParameter("fechaFin");
		DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");

		// Validación de campos
		if (nombre == null || nombre.isBlank() || sigla == null || sigla.isBlank() || ciudad == null || ciudad.isBlank()
				|| pais == null || pais.isBlank() || fechaIniStr == null || fechaIniStr.isBlank() || fechaFinStr == null
				|| fechaFinStr.isBlank()) {

			request.setAttribute("error", "Todos los campos son requeridos");
			request.setAttribute("evento", evento);
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
			return;
		}

		try {
			LocalDate fechaIni = LocalDate.parse(fechaIniStr);
			LocalDate fechaFin = LocalDate.parse(fechaFinStr);

			if (fechaFin.isBefore(fechaIni)) {
				request.setAttribute("error", "La fecha de fin no puede ser anterior a la de inicio");
				request.setAttribute("evento", evento);
				request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
				return;
			}

			if (session == null || dataU.getNickname() == null) {
				request.setAttribute("error", "Sesión no válida. Inicie sesión nuevamente.");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				return;
			}

			String org = dataU.getNickname();
			LocalDate fechaActual = LocalDate.now();

			DataEdicion dataEd = new DataEdicion(nombre, sigla, fechaIni, fechaFin, fechaActual, ciudad, pais);
			IEV.nuevaEdicion(dataEd, evento, org);

			response.sendRedirect(request.getContextPath() + "/home");

		} catch (EdicionRepetidaExcepcion e) {
			request.setAttribute("error", "Ya existe una edición con ese nombre.");
			request.setAttribute("evento", evento);
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);

		}
	}
}
