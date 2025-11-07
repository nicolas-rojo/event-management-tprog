package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.miseventos.utils.fabricaWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;

/**
 * Servlet implementation class VerificacionAJAX
 */
@WebServlet("/verificacionajax")
public class VerificacionAJAX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuarioWS ICU_WS;

	@Override
	public void init() throws ServletException {
		ICU_WS = fabricaWS.getControladorUsuarioWS();
		System.out.println("VerificacionAJAX");
	}   
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo"); //Nickname o Email
		String valor = request.getParameter("valor"); //Valor correspondiente al tipo
		
		boolean existe = false;
		
		if("mail".equals(tipo)) {
			existe = ICU_WS.existeEmail(valor);
		}else {
			existe = ICU_WS.existeNickname(valor);
		}
		
		response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(existe ? "existe" : "noexiste");
			
	}



}
