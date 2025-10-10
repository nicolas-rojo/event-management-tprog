<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.miseventos.utils.nombreUtils" %>

<%
    DataEventoCompleto evento = (DataEventoCompleto) request.getAttribute("evento");
    List<DataEdicion> ediciones = (List<DataEdicion>) request.getAttribute("ediciones");
    String tipo = (String) session.getAttribute("tipoUsr");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	<jsp:include page="/WEB-INF/template/topbar.jsp" />
	
	<!-- SIDEBAR -->
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />

    <!-- Contenido -->
    <div class="content">
        <div class="contenedores">
            <div class="contenedor-principal">
            	<% String nomNormalEv = nombreUtils.normalizarNombre(evento.getNombre()); %>
            	<img class="imagenes" 
					src="${pageContext.request.contextPath}/resources/images/EV-<%= nomNormalEv %>.png" 
					alt="logoEvento" 
					width="150px" height="150px"
					onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
                <div class="informacion-evento">
                    <h2 class="nombre-evento"><%= evento.getNombre() %></h2>
                    <p class="descripcion-evento"><%= evento.getDescripcion() != null ? evento.getDescripcion() : "" %></p>
                    <div class="detalles-evento">
                        <span class="siglas">SIG: <%= evento.getSigla() %></span>
                        <span class="fecha-alta">Alta: <%= evento.getFechaAlta() %></span>
                    </div>
                    <% if ("organizador".equals(tipo)) { %>
                    <div id="botondiv1" class="boton-div">
                        <a href="<%= request.getContextPath() %>/altaEdicion">
                            <button id="boton1" class="btn-nuevaedicion">+ Nueva edicion</button>
                        </a>
                    </div>
                    <% } %>
                </div>
            </div>

            <h2 class="texto-ed">Ediciones:</h2>

            <div class="contenedorEdiciones">
                <% 
                if (ediciones != null && !ediciones.isEmpty()) {
                    for (DataEdicion edicion : ediciones) {
                        String eventoEncoded = java.net.URLEncoder.encode(evento.getNombre(), "UTF-8");
                        String edicionEncoded = java.net.URLEncoder.encode(edicion.getNombre(), "UTF-8");
            			String nomNormal = nombreUtils.normalizarNombre(edicion.getNombre());
                %>
                <a href="<%= request.getContextPath() %>/consultaEdicionEvento?evento=<%= eventoEncoded %>&edicion=<%= edicionEncoded %>" class="contenedor-link">
                    <div class="contenedor">
                        <img class="imagenes" 
								src="${pageContext.request.contextPath}/resources/images/ED-<%= nomNormal %>.png" 
								alt="logoEvento" 
								width="120px" height="120px"
								onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
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