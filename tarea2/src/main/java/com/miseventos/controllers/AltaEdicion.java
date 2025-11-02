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

import logica.Fabrica;
import logica.interfaces.IEventos;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataUsuario;
import excepciones.EdicionRepetidaExcepcion;
import excepciones.LinkInvalidoExcepcion;

@MultipartConfig
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
		String videoUrl = request.getParameter("videoUrl");
		DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");

		// Validación de campos
		if (nombre == null || nombre.isBlank() || sigla == null || sigla.isBlank() || ciudad == null || ciudad.isBlank()
				|| pais == null || pais.isBlank() || fechaIniStr == null || fechaIniStr.isBlank() || fechaFinStr == null
				|| fechaFinStr.isBlank()) {

			request.setAttribute("error", "Todos los campos son requeridos");
			request.setAttribute("evento", evento);
			
			request.setAttribute("nombreEd", nombre);
			request.setAttribute("sigla", sigla);
			request.setAttribute("ciudad", ciudad);
			request.setAttribute("pais", pais);
			request.setAttribute("fechaIni", fechaIniStr);
			request.setAttribute("fechaFin", fechaFinStr);
			request.setAttribute("videoUrl", videoUrl);
			
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
			return;
		}

		try {
			LocalDate fechaIni = LocalDate.parse(fechaIniStr);
			LocalDate fechaFin = LocalDate.parse(fechaFinStr);

			if (fechaFin.isBefore(fechaIni)) {
				request.setAttribute("error", "La fecha de fin no puede ser anterior a la de inicio");
				request.setAttribute("evento", evento);
				
				request.setAttribute("nombreEd", nombre);
				request.setAttribute("sigla", sigla);
				request.setAttribute("ciudad", ciudad);
				request.setAttribute("pais", pais);
				request.setAttribute("fechaIni", fechaIniStr);
				request.setAttribute("fechaFin", fechaFinStr);
				request.setAttribute("videoUrl", videoUrl);
				
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

			DataEdicion dataEd = new DataEdicion(nombre, sigla, fechaIni, fechaFin, fechaActual, ciudad, pais, videoUrl);
			IEV.nuevaEdicion(dataEd, evento, org);
			cargarImg(request, nombre);

			response.sendRedirect(request.getContextPath() + "/home");

		} catch (EdicionRepetidaExcepcion e) {
			request.setAttribute("error", "Ya existe una edición con ese nombre.");
			request.setAttribute("evento", evento);
			
			request.setAttribute("sigla", sigla);
			request.setAttribute("ciudad", ciudad);
			request.setAttribute("pais", pais);
			request.setAttribute("fechaIni", fechaIniStr);
			request.setAttribute("fechaFin", fechaFinStr);
			request.setAttribute("videoUrl", videoUrl);
			
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);

		} catch (LinkInvalidoExcepcion e) {
			request.setAttribute("error", "El link ingresado no es un link de YouTube válido");
			request.setAttribute("evento", evento);
			
			request.setAttribute("nombreEd", nombre);
			request.setAttribute("sigla", sigla);
			request.setAttribute("ciudad", ciudad);
			request.setAttribute("pais", pais);
			request.setAttribute("fechaIni", fechaIniStr);
			request.setAttribute("fechaFin", fechaFinStr);
			
			request.getRequestDispatcher("/WEB-INF/altaEdicion.jsp").forward(request, response);
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
