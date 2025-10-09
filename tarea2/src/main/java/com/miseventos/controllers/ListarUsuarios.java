package com.miseventos.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logica.Fabrica;
import logica.interfaces.IUsuario;
import logica.datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;

@WebServlet("/consultaUsuario")
public class ListarUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUsuario ICU;
    
    @Override
    public void init() throws ServletException {
        ICU = Fabrica.getInstance().getIControladorUsuario();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            DataUsuario[] usuarios = ICU.getUsuarios();
            
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
                   
        } catch (UsuarioNoExisteException e) {
            request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
            request.getRequestDispatcher("/WEB-INF/listarUsuarios.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}