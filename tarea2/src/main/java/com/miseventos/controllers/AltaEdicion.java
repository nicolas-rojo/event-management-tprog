package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import com.miseventos.utils.nombreUtils;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.DataUsuario;
import cliente.ws.eventos.DataEdicion;

@MultipartConfig
@WebServlet("/AltaEdicion")
public class AltaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;

	@Override
	public void init() throws ServletException {
		ControladorEventoWSService servicio = new ControladorEventoWSService();
		IEV_WS = servicio.getControladorEventoWSPort();
		System.out.println("AltaEdicionWS");
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
			DataEdicion dt = new DataEdicion();
			dt.setNombre(nombre);
			dt.setSigla(sigla);
			dt.setFechaFin(fechaFin.toString());
			dt.setFechaIni(fechaIni.toString()); 
			dt.setPais(pais);
			dt.setCiudad(ciudad);
			dt.setFechaAlta(fechaActual.toString());
			IEV_WS.nuevaEdicion(dt, evento, org);
			cargarImg(request, nombre);

			response.sendRedirect(request.getContextPath() + "/home");

		//} catch (EdicionRepetidaExcepcion e) {
		//	request.setAttribute("error", "Ya existe una edición con ese nombre.");
		//	request.setAttribute("evento", evento);
		//	request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);

		} catch(Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta la edicion");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
	
	private void cargarImg(HttpServletRequest request, String nick) throws IOException, ServletException {
		Part filePart = request.getPart("imagen");
		if (filePart == null || filePart.getSize() == 0) {
			return; //NO SE SUBIO NINGUNA IMAGEN
		}
		
		String nomNorm = nombreUtils.normalizarNombre(nick);
		
		BufferedImage imagen = ImageIO.read(filePart.getInputStream());
		String rutaRel = "/resources/images/ED-" + nomNorm + ".png";
		String rutaAbs = getServletContext().getRealPath(rutaRel);
		
		File archivoDest = new File(rutaAbs);
		ImageIO.write(imagen, "png", archivoDest);		
	}
}
