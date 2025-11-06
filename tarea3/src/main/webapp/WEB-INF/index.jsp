<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import= "cliente.ws.usuarios.*"%>
<%@ page import= "cliente.ws.eventos.*"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home :: Mis Eventos</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/listarEventosStyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">
</head>

<body>
    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

	<!-- CARDS -->
	<div class="events-container">
		<%
        List<DataEventoCompleto> eventos = (List<DataEventoCompleto>) request.getAttribute("eventos");
        if (eventos != null) {
            for (DataEventoCompleto evento : eventos) {
                String nomNormal = nombreUtils.normalizarNombre(evento.getNombre());
    	%>
    			<a href="<%=request.getContextPath()%>/listarEdiciones?evento=<%=java.net.URLEncoder.encode(evento.getNombre(), "UTF-8")%>" class="event-card">
					<div class="event-card-image">
						<img class="imagenes"
                         	src="/tarea2/resources/images/EV-<%= nomNormal %>.png"
                         	alt="logoEvento" width="120px" height="120px"
                         	onerror="this.onerror=null; this.src='/tarea2/resources/images/IMG-NO.png';">
					</div>
			  
					<div class="event-card-content">
						<h3 class="event-card-title"><%= evento.getNombre() %></h3>
						<p class="event-card-description"><%= evento.getDescripcion() %></p>
					</div>
				</a>
    	<%
            }
        }
    	%>
	</div>
	
    <!-- LOWBAR -->
    <jsp:include page="/WEB-INF/template/lowbar.jsp" />
</body>
</html>