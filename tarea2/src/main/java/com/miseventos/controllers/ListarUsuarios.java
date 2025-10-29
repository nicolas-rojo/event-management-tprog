package com.miseventos.controllers;

import java.io.IOException;

import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import java.util.List;
import cliente.ws.usuarios.DataUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/consultaUsuario")
public class ListarUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorUsuarioWS ICU_WS;
    
    @Override
    public void init() throws ServletException {      
    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
        ICU_WS = servicio2.getControladorUsuarioWSPort();
        System.out.println("ListarUsuariosWS");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            List<DataUsuario> u = ICU_WS.getUsuarios().getItem();
            DataUsuario[] usuarios = u.toArray(new DataUsuario[0]);
            
            if (usuarios != null && usuarios.length > 0) {
                // Ordenar usuarios alfabéticamente por nombre
                java.util.Arrays.sort(usuarios, new java.util.Comparator<DataUsuario>() {
                    @Override
                    public int compare(DataUsuario u1, DataUsuario u2) {
                        int resultado = u1.getNombre().compareToIgnoreCase(u2.getNombre());
                        if (resultado == 0) {
                            resultado = u1.getNickname().compareToIgnoreCase(u2.getNickname());
                        }
                        return resultado;
                    }
                });
                
                request.setAttribute("usuarios", usuarios);
            } else {
                request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
            }
            
            request.getRequestDispatcher("/WEB-INF/listarUsuarios.jsp").forward(request, response);
                   
        //} catch (UsuarioNoExisteException e) {
        //    request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
        //    request.getRequestDispatcher("/WEB-INF/listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "Error al cargar los usuarios registrados del sistema");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}