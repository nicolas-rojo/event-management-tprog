package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.time.LocalDate;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.usuarios.DataUsuario;
import cliente.ws.eventos.DataTRegistro;

import com.miseventos.utils.fabricaWS;

@WebServlet("/altaRegistro")
@MultipartConfig

public class AltaRegistro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
	
	@Override
    public void init() throws ServletException {  
		IEV_WS = fabricaWS.getControladorEventoWS();
		ICU_WS = fabricaWS.getControladorUsuarioWS();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String edicion = request.getParameter("edicion");
		String evento = request.getParameter("evento");
		String treg = request.getParameter("treg");
		DataTRegistro dataTR = IEV_WS.getDataTRegistro(evento, edicion, treg);
		request.setAttribute("dataTR", dataTR);		
		request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
	    String asistente = datosU.getNickname();		
	    String evento = request.getParameter("evento");
	    String edicion = request.getParameter("edicion");
	    String tiporegistro = request.getParameter("treg");
	    LocalDate fecha = LocalDate.now();
	    
	    try {
	        ICU_WS.nuevoRegistro(asistente, evento, edicion, tiporegistro, fecha.toString());
	        response.sendRedirect(request.getContextPath() + "/home");
	    } catch(cliente.ws.usuarios.AsistenteYaRegistrado_Exception e) {
	        // Recuperar dataTR antes de reenviar
	       DataTRegistro dataTR = IEV_WS.getDataTRegistro(evento, edicion, tiporegistro);
	        request.setAttribute("dataTR", dataTR);
	        request.setAttribute("errorYaRegistrado", "Ya estás registrado a esta edición");
	        request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
	    } catch(cliente.ws.usuarios.NoHayCupoEdicionTRegistro_Exception e) {
	        // Recuperar dataTR antes de reenviar
	        DataTRegistro dataTR = IEV_WS.getDataTRegistro(evento, edicion, tiporegistro);
	        request.setAttribute("dataTR", dataTR);
	        request.setAttribute("errorCupo", "No hay cupos disponibles para esta edición");
	        request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
	    } catch(cliente.ws.usuarios.FechaRegistroInvalidaException_Exception e) {
	        // Recuperar dataTR antes de reenviar
	        DataTRegistro dataTR = IEV_WS.getDataTRegistro(evento, edicion, tiporegistro);
	        request.setAttribute("dataTR", dataTR);
	        request.setAttribute("errorFecha", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/altaRegistro.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "No se pudo dar de alta el registro");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	    }
	}
		
	
}