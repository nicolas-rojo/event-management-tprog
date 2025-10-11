<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">

<%
String tipo = (String) session.getAttribute("tipoUsr");
List<String> categorias = (List<String>) request.getAttribute("categorias");
String categoriaSeleccionada = (String) request.getAttribute("categoriaSeleccionada");
%>

<div class="sidebar">

	<!-- Menú según tipo de usuario -->
    <%
    if ("organizador".equals(tipo)) {
    %>
    
    <a href="" id="miPerfil" class="elementTitle" style="display: flex;">Mi Perfil</a>
    <a href="${pageContext.request.contextPath}/altaEvento" class="element">Alta Evento</a>
    <a href="" class="element">Alta Institución</a>
    <a href="" class="element">Nuevo Tipo de Registro</a>
    <a href="" class="element">Nuevo Patrocinio</a>
    
    <%
    } else if ("asistente".equals(tipo)) {
    %>
    
    <a href="" id="miPerfil" class="elementTitle" style="display: flex;">Mi Perfil</a>
    <a href="" class="element">Ver Registros</a>
    
    <%
    }
    %>
    
    <a href="${pageContext.request.contextPath}/home" class="element">Consultar Evento</a>
    <a href="${pageContext.request.contextPath}/consultaUsuario" class="element">Consultar Usuario</a>
    
    <!-- Filtro de Categorías -->
    <% if (categorias != null && !categorias.isEmpty()) { %>
    <div class="categorias-sidebar">
        <h3 class="categorias-title">Categorías</h3>
        <div class="categorias-box">
            <a href="${pageContext.request.contextPath}/home" 
               class="categoria-item <%= (categoriaSeleccionada == null) ? "activa" : "" %>">
                Todas
            </a>
            <% for (String categoria : categorias) { %>
                <a href="${pageContext.request.contextPath}/home?categoria=<%= java.net.URLEncoder.encode(categoria, "UTF-8") %>" 
                   class="categoria-item <%= (categoria.equals(categoriaSeleccionada)) ? "activa" : "" %>">
                    <%= categoria %>
                </a>
            <% } %>
        </div>
    </div>
    <% } %>
</div>