<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "cliente.ws.usuarios.*"%>
<%@ page import= "cliente.ws.eventos.*"%>
<%@ page import= "cliente.ws.eventos.DataEdicion"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miseventos.utils.nombreUtils"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Ediciones :: Mis Eventos</title>
	
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/listarEdicionesStyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">
</head>

<body>
	<!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />
    
    <!-- CARDS -->
    <div class="events-container">
    	<%
		String evento = (String) request.getAttribute("evento");
		List<DataEdicion> ediciones = (List<DataEdicion>) request.getAttribute("ediciones");
		if (ediciones != null && !ediciones.isEmpty()) {
			for (DataEdicion edicion : ediciones) {
                String nomNormal = nombreUtils.normalizarNombre(edicion.getNombre());
				String eventoEncoded = java.net.URLEncoder.encode(evento, "UTF-8");
                String edicionEncoded = java.net.URLEncoder.encode(edicion.getNombre(), "UTF-8");
		%>
				<a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= eventoEncoded %>&edicion=<%= edicionEncoded %>" class="event-card">
					<div class="event-card-image">
						<img class="imagenes"
                         	src="/tarea2/resources/images/ED-<%= nomNormal %>.png"
                         	alt="logoEvento" width="120px" height="120px"
                         	onerror="this.onerror=null; this.src='/tarea2/resources/images/IMG-NO.png';">
					</div>
			  
					<div class="event-card-content">
						<h3 class="event-card-title"><%= edicion.getNombre() %></h3>
						<p class="event-card-description">SIGLA: <%= edicion.getSigla() %></p>
						<p class="event-card-description"><%= edicion.getCiudad() %>, <%= edicion.getPais() %></p>
						<p class="event-card-description">Desde: <%= edicion.getFechaIni() %> - Hasta: <%= edicion.getFechaFin() %></p>
					</div>
				</a>
		<%
			}
		} else {
		%>
			<div class="no-events-message">
				<i class="bi bi-calendar-x"></i>
				<p>No hay ediciones para el evento seleccionado</p>
			</div>
		<%
		}
		%>
    </div>
    
    <!-- LOWBAR -->
    <jsp:include page="/WEB-INF/template/lowbar.jsp" />
</body>
</html>