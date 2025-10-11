package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import com.miseventos.utils.*;
import logica.Fabrica;
import logica.interfaces.*;
import logica.datatypes.*;
import excepciones.*;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
	
	@Override
    public void init() throws ServletException {  
    	ICU = Fabrica.getInstance().getIControladorUsuario();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String tipo = request.getParameter("tipo");
		String nombre = request.getParameter("nombre");
		String nick = request.getParameter("nick");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		
		try {
			HttpSession session = request.getSession();
			if ("organizador".equals(tipo)) {
                String url = request.getParameter("url");
                String desc = request.getParameter("desc");
				ICU.registrarOrganizador(new DataOrganizador(nombre, nick, mail, pass, desc, url));
				session.setAttribute("tipoUsr", "organizador");
			} else if ("asistente".equals(tipo)) {
				String apellido = request.getParameter("apellido");
				LocalDate fechaNac = LocalDate.parse(request.getParameter("fechaNac"));
				ICU.registrarAsistente(new DataAsistente(nombre, nick, mail, pass, apellido, fechaNac));
				session.setAttribute("tipoUsr", "asistente");
			} else {
				System.out.println(nombre + nick);
				request.setAttribute("error", "Error Desconocido");
				request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
				return;
			}
			cargarImg(request, nick);
			DataUsuario datos = new DataUsuario(nombre, nick, mail, "");
			session.setAttribute("datosUsr", datos);
			response.sendRedirect(request.getContextPath() + "/home");
		} catch (UsuarioRepetidoException e) {
			request.setAttribute("error", "Mail o Nickname en uso");
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error al intentar registrar");
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
		String rutaRel = "/resources/images/USR-" + nomNorm + ".png";
		String rutaAbs = getServletContext().getRealPath(rutaRel);
		
		File archivoDest = new File(rutaAbs);
		ImageIO.write(imagen, "png", archivoDest);		
	}
}
