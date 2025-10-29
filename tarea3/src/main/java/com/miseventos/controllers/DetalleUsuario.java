package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.datatypes.DataAsistente;
import logica.datatypes.DataEdicionWeb;
import logica.datatypes.DataOrganizador;
import logica.datatypes.DataUsuario;
import logica.datatypes.ParEdicionRegistro;
import logica.interfaces.IEventos;
import logica.interfaces.IUsuario;

import java.io.IOException;
import java.util.List;

import excepciones.UsuarioNoExisteException;

@WebServlet("/detalleUsuario")
public class DetalleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUsuario ICU;
    private IEventos IEV;
    
    @Override
    public void init() throws ServletException {
        ICU = Fabrica.getInstance().getIControladorUsuario();
        IEV = Fabrica.getInstance().getIControladorEventos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DataUsuario usr = (DataUsuario) session.getAttribute("datosUsr");
		try {
			DataAsistente asistente = ICU.getAsistente(usr.getEmail());			
			request.setAttribute("asistente", asistente);
		} catch (UsuarioNoExisteException e) {
            request.setAttribute("error", "El usuario solicitado no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo obtener la informacion del usuario");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
		List<ParEdicionRegistro> registros = ICU.getRegistrosAsistente(usr.getNickname());
        request.setAttribute("registros", registros);
        
        request.getRequestDispatcher("/WEB-INF/detalleUsuario.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
