<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%
    DataEventoCompleto evento = (DataEventoCompleto) request.getAttribute("evento");
    List<DataEdicion> ediciones = (List<DataEdicion>) request.getAttribute("ediciones");
    
    if (evento == null) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= evento.getNombre() %> - Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/styles/consultaEventoStyle.css">
</head>

<body>
    <!-- TOPBAR -->
    <div class="topbar">
        <div class="topbar-logo">
            <a href="<%= request.getContextPath() %>/home">
                <img src="<%= request.getContextPath() %>/resources/images/logo.png" alt="Logo">
            </a>
        </div>

        <div class="topbar-search">
            <input type="text" placeholder="Buscar evento, edicion... 🔍">
        </div>

        <div class="topbar-right">
            <% if (session.getAttribute("usuario") != null) { %>
                <a href="<%= request.getContextPath() %>/logout">Cerrar Sesión</a>
                <a href="<%= request.getContextPath() %>/consultaUsuario">
                    <img alt="logo" src="<%= request.getContextPath() %>/resources/images/IMG-NO.png" class="logo">
                </a>
            <% } else { %>
                <a href="<%= request.getContextPath() %>/login">Iniciar Sesión</a>
            <% } %>
        </div>
    </div>

    <!-- SIDEBAR -->
    <div class="sidebar">
        <a href="<%= request.getContextPath() %>/consultaUsuario" id="miPerfil" class="elementTitle" style="display: flex;">Mi Perfil</a>
        <a href="<%= request.getContextPath() %>/consultaUsuario" class="element">Ver Registros</a>
        <a href="<%= request.getContextPath() %>/home" class="element">Consultar Evento</a>
        <a href="<%= request.getContextPath() %>/consultaUsuario/lista" class="element">Consultar Usuario</a>
    </div>

    <!-- Contenido -->
    <div class="content">
        <div class="contenedores">
            <div class="contenedor-principal">
                <img class="imagenes" 
                     src="<%= request.getContextPath() %>/resources/images/IMG-NO.png" 
                     alt="<%= evento.getNombre() %>"
                     width="150px" height="150px">
                <div class="informacion-evento">
                    <h2 class="nombre-evento"><%= evento.getNombre() %></h2>
                    <p class="descripcion-evento"><%= evento.getDescripcion() %></p>
                    <div class="detalles-evento">
                        <span class="siglas">SIG: <%= evento.getSigla() %></span>
                        <span class="fecha-alta">Alta: <%= evento.getFechaAlta() %></span>
                    </div>
                </div>
            </div>

            <h2 class="texto-ed">Ediciones:</h2>

            <div class="contenedorEdiciones">
                <% 
                if (ediciones != null && !ediciones.isEmpty()) {
                    for (DataEdicion edicion : ediciones) {
                %>
                <a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= java.net.URLEncoder.encode(evento.getNombre(), "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(edicion.getNombre(), "UTF-8") %>" class="contenedor-link">
                    <div class="contenedor">
                        <img class="imagenes" 
                             src="<%= request.getContextPath() %>/resources/images/IMG-NO.png" 
                             alt="<%= edicion.getNombre() %>"
                             width="120px" height="120px">
                        <div class="informacion-edicion">
                            <h2 class="nombre-evento"><%= edicion.getNombre() %></h2>
                            <div class="detalles-edicion">
                                <span class="siglas">SIGLA: <%= edicion.getSigla() %></span>
                                <span class="pais"><%= edicion.getCiudad() %>, <%= edicion.getPais() %></span>
                                <span class="fechas">Fecha Alta: <%= edicion.getFechaAlta() %></span>
                                <span class="fechas">Desde: <%= edicion.getFechaAlta() %> - Hasta: <%= edicion.getFechaFin() %></span>
                            </div>
                        </div>
                    </div>
                </a>
                <% 
                    }
                } else {
                %>
                <p style="color: #f1eeee; text-align: center; margin-top: 20px;">
                    No hay ediciones disponibles para este evento.
                </p>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>