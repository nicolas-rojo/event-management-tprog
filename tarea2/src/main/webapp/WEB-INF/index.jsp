<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="cliente.ws.eventos.DataEventoCompleto"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<title>Principal :: Mis Eventos</title>

<!-- Fuente -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/indexStyle.css">
</head>

<body>
	<!-- TOPBAR -->
	<jsp:include page="/WEB-INF/template/topbar.jsp" />

	<!-- SIDEBAR -->
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />

	<div class="content">
		<div class="contenedores">
			<%
			List<DataEventoCompleto> eventos = (List<DataEventoCompleto>) request.getAttribute("eventos");
			if (eventos != null) {
				for (DataEventoCompleto evento : eventos) {
					String nomNormal = nombreUtils.normalizarNombre(evento.getNombre());
			%>
			<a
				href="<%=request.getContextPath()%>/consultaEvento?evento=<%=java.net.URLEncoder.encode(evento.getNombre(), "UTF-8")%>"
				class="contenedor-link">
				<div class="contenedor">
					<img class="imagenes"
						src="${pageContext.request.contextPath}/resources/images/EV-<%= nomNormal %>.png"
						alt="logoEvento" width="120px" height="120px"
						onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
					<div class="informacion">
						<h2><%=evento.getNombre()%></h2>
						<p><%=evento.getDescripcion()%></p>
						<%
						if ("organizador".equals(session.getAttribute("tipoUsr"))) {
						%>
						<form action="<%=request.getContextPath()%>/home" method="post"
							style="display: inline;">
							<input type="hidden" name="eventoDarBaja"
								value="<%=evento.getNombre()%>" />
								<div id="botondiv1" class="boton-div">
									<button type="submit" class="btn-nuevaedicion">Dar de baja</button>
								</div>	
						</form>
						<%
						}
						%>
					</div>
				</div>
			</a>
			<%
			}
			}
			%>
		</div>
	</div>
</body>

</html>