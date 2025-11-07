package com.miseventos.controllers;

import java.io.IOException;
import java.util.List;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cliente.ws.usuarios.DataUsuario;
import cliente.ws.usuarios.DataAsistente;
import cliente.ws.usuarios.DataOrganizador;
import cliente.ws.usuarios.ParEdicionRegistro;
import cliente.ws.eventos.DataEdicionWeb;
import cliente.ws.usuarios.UsuarioNoExisteException_Exception;
import com.miseventos.utils.fabricaWS;
@WebServlet("/detalleUsuario")
public class DetalleUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;
    
    @Override
    public void init() throws ServletException {
        
    	IEV_WS = fabricaWS.getControladorEventoWS();
    	ICU_WS = fabricaWS.getControladorUsuarioWS();
        System.out.println("DetalleUsuarioWS");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        if (email == null || email.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/consultaUsuario");
            return;
        }
        
        try {
            String tipoUsuario = ICU_WS.getTipoUsuario(email);
            
            if ("Asistente".equals(tipoUsuario)) {
                DataAsistente asistente = ICU_WS.getAsistente(email);
                request.setAttribute("usuario", asistente);
                request.setAttribute("tipo", "Asistente");
                
                // Cargar registros del asistente
                List<ParEdicionRegistro> registros = ICU_WS.getRegistrosAsistente(asistente.getNickname()).getItem();
                request.setAttribute("registros", registros);
                
            } else if ("Organizador".equals(tipoUsuario)) {
                DataOrganizador organizador = ICU_WS.getOrganizador(email);
                request.setAttribute("usuario", organizador);
                request.setAttribute("tipo", "Organizador");
                
                // Cargar ediciones del organizador
                String nick = organizador.getNickname();
                List<DataEdicionWeb> ediciones_aux = IEV_WS.getEdicionesEventoOrganizadorWeb(nick).getItem();
                DataEdicionWeb[] ediciones = ediciones_aux.toArray(new DataEdicionWeb[0]);
                request.setAttribute("ediciones", ediciones);
            }

            List<String> seguidos = ICU_WS.getSeguidos(email).getItem();
            List<String> seguidores = ICU_WS.getSeguidores(email).getItem();
            request.setAttribute("seguidos", seguidos);
            request.setAttribute("seguidores", seguidores);
    
            request.getRequestDispatcher("/WEB-INF/detalleUsuario.jsp").forward(request, response);
                   
        } catch (UsuarioNoExisteException_Exception e) {
            request.setAttribute("error", "El usuario solicitado no existe.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudo obtener la informacion del usuario");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        String emailASeguir = request.getParameter("emailASeguir");
        DataUsuario usr = (DataUsuario) request.getSession().getAttribute("datosUsr");
        String loggedMail = usr.getEmail();     
        
        // Si hay una acción de seguir/dejar de seguir
        if (accion != null && emailASeguir != null && loggedMail != null) {
            try {
                if ("seguir".equals(accion)) {
                    ICU_WS.seguirUsuario(loggedMail, emailASeguir);
                } else if ("dejar_seguir".equals(accion)) {
                    ICU_WS.dejarDeSeguir(loggedMail, emailASeguir);
                }
                
                response.sendRedirect(request.getContextPath() + "/detalleUsuario?email=" + emailASeguir);
                return;
                
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al intentar seguir. Intente nuevamente.");
    	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
        }
        
        doGet(request, response);
    }
}
