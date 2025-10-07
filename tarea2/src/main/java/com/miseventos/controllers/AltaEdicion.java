package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.interfaces.IEventos;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.EdicionRepetidaExcepcion;
import logica.datatypes.*;

@WebServlet("/AltaEdicion")
public class AltaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEventos IEV;

	@Override
	public void init() throws ServletException {
		IEV = Fabrica.getInstance().getIControladorEventos();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String nombre = request.getParameter("nombreEd");
		String sigla = request.getParameter("sigla");
		String ciudad = request.getParameter("ciudad");
		String pais = request.getParameter("pais");
		String fechaIni = request.getParameter("fechaIni");
		LocalDate fechaIniFormato = LocalDate.parse(fechaIni);
		String fechaFin = request.getParameter("fechaFin");
		LocalDate fechaFinFormato = LocalDate.parse(fechaFin);
		LocalDate fechaActual = LocalDate.now();

		if (!fechaFinFormato.isAfter(fechaIniFormato)) {

			request.setAttribute("error", "La fecha de fin debe ser posterior a la fecha de inicio");
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
			return;

		} else {
			HttpSession session = request.getSession();
			String org = (String) session.getAttribute("nickmail");
			DataEdicion dataEd = new DataEdicion(nombre, sigla, fechaIniFormato, fechaFinFormato, fechaActual, ciudad,
					pais);
			String evento = null;

			try {
				IEV.nuevaEdicion(dataEd, evento, org);
				response.sendRedirect(request.getContextPath() + "/home");
			} catch (EdicionRepetidaExcepcion e) {
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
			}
		}
	}
}