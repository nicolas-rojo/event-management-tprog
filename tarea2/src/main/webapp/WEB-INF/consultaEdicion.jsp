<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="logica.datatypes.*"%>
<%@ page import="com.miseventos.utils.nombreUtils" %>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">

<%
String tipo = (String) request.getAttribute("tipoUsr");
DataEdicion dataEd = (DataEdicion) request.getAttribute("dataEdicion");
DataOrganizador dataOrg = (DataOrganizador) request.getAttribute("dataOrganizador");
List<DataTRegistro> dataTRegistros = (List<DataTRegistro>) request.getAttribute("dataTRegistros");
List<DataPatrocinioCompleto> dataPatrocinios = (List<DataPatrocinioCompleto>) request.getAttribute("dataPatrocinios");
%>

<title>Mis Eventos :: Consulta de Edición</title>

<!-- Fuente -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/consultaEdicionStyle.css">

<style>
/* Quitar color y subrayado de los links del organizador */
.link-sin-estilo {
    text-decoration: none;
    color: inherit;
    display: block;
}
.link-sin-estilo:hover .contenedor-derecha {
    background-color: #e0e0e0;
    box-shadow: 0px 12px 25px rgba(0, 0, 0, 0.3);
}
</style>

</head>

<body>
	<!-- TOPBAR -->
	<jsp:include page="/WEB-INF/template/topbar.jsp" />

	<!-- SIDEBAR -->
	<jsp:include page="/WEB-INF/template/sidebar.jsp" />

	<!-- Contenido -->
	<div class="content">
		<div class="columna-izquierda"></div>

		<%
		if (dataOrg != null && dataEd != null) {
			String nomNormal = nombreUtils.normalizarNombre(dataEd.getNombre());
			String nomNormal1 = nombreUtils.normalizarNombre(dataOrg.getNickname());
		%>

		<!-- Columna central -->
		<div class="columna-central">
			<div class="contenedor-principal">
				<img class="imagenes"
					src="${pageContext.request.contextPath}/resources/images/ED-<%= nomNormal %>.png"										
					alt="logoEdición" 
					width="150px" height="150px"
					onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">					
				<div class="informacion-evento">
					<h2 class="nombre-evento" id="nombreMaraton"><%=dataEd.getNombre()%></h2>
					<div class="detalles-evento">
						<span class="pais"><%=dataEd.getPais()%></span>
						<span class="fechas">Desde: <%=dataEd.getFechaIni()%> - Hasta: <%=dataEd.getFechaFin()%></span>
						<span class="fecha-alta">Alta: <%=dataEd.getFechaAlta()%></span>
						<span class="siglas">SIG: <%=dataEd.getSigla()%></span>
					</div>
				</div>
			</div>

			<%
			if (dataTRegistros != null && !dataTRegistros.isEmpty()) {
			%>
			<h2 class="texto-ed">Tipos de Registro:</h2>
			<div class="contenedorTRegistros">
				<%
				for (DataTRegistro dataTR : dataTRegistros) {
				%>
				<div class="contenedor">
					<div class="informacion-TRegistro">
						<h2 class="nombre-evento"><%=dataTR.getNombre()%></h2>
						<div class="detalles-TRegistro">
							<span class="descripcion-TRegistro"><%=dataTR.getDescr()%>.</span>
							<span class="costo-TRegistro">Costo: $<%=dataTR.getCosto()%>.</span>
							<span class="Cupos-TRegistro"><%=dataTR.getCupo()%> cupos restantes.</span>
							<%
							if ("asistente".equals(session.getAttribute("tipoUsr")) && (request.getAttribute("registrado").equals(false))) {
							%>
							<div id="botondiv1" class="boton-div" style="display: flex;">
								<button type="button" class="btn-inscribirse"
									onclick="window.location.href='${pageContext.request.contextPath}/altaregistro?evento=<%= request.getAttribute("evento") %>&edicion=<%= dataEd.getNombre() %>'">
									Inscribirse
								</button>
							</div>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<%
			}
			%>

			<%
			if (dataPatrocinios != null && !dataPatrocinios.isEmpty()) {
			%>
			<h2 class="texto-ed">Patrocinan:</h2>
			<div class="contenedorTRegistros">
				<%
				for (DataPatrocinioCompleto dataP : dataPatrocinios) {
				%>
				<div class="contenedor">
					<img class="imagenes"
						src="<%=request.getContextPath()%>/resources/images/IMG-NO.png"
						alt="logoPatro" width="120px" height="120px">
					<div class="informacion-edicion">
						<h2 class="nombre-evento"><%=dataP.getInstitucion()%></h2>
						<div class="detalles-edicion">
							<span class="fecha-patrocinio">Fecha: <%=dataP.getFecha()%></span>
							<span class="aporte-patrocinio">Aporte: $<%=dataP.getMonto()%>.</span>
							<span class="nivel-patrocinio">Nivel: <%=dataP.getNivel()%>.</span>
							<span class="tipo-patrocinio">Código: <%=dataP.getCod()%>.</span>
							<span class="Cupos-patrocinio">Cupos: <%=dataP.getCtdCupo()%>.</span>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
			<%
			}
			%>
		</div>

		<!-- Columna derecha -->
		<div class="columna-derecha">
			<h2 class="texto-og">Organiza:</h2>

			<a href='${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(dataOrg.getEmail(), "UTF-8") %>'
               class="link-sin-estilo">
				<div class="contenedor-derecha">
					<img class="imagenes"
						src="${pageContext.request.contextPath}/resources/images/USR-<%= nomNormal1 %>.png"										
					alt="logoEdición" 
					width="120px" height="120x"
					onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
					<div class="informacion-TRegistro">
						<h2 class="nombre-og"><%=dataOrg.getNombre()%></h2>
						<div class="detalles-TRegistro">
							<span class="email-org"><%=dataOrg.getEmail()%></span>
						</div>
					</div>
				</div>
			</a>

			<%
			if ("asistente".equals(session.getAttribute("tipoUsr")) && (request.getAttribute("registrado").equals(true))) {
			%>
			<h2 class="texto-registro" id="registro-titulo">Registro:</h2>

			<div class="contenedor-derecha-alt" id="registro-detalle" style="display: flex;">
				<div class="informacion-TRegistro">
					<div class="detalles-TRegistro">
						<%
						DataUsuario dataU = (DataUsuario) session.getAttribute("dataUsr");
						%>
						<div class="registro-item"><%=dataU.getNickname()%></div>

						<%
						Boolean registrado = (Boolean) request.getAttribute("registrado");
						if (registrado != null && registrado) {
							ParEdicionRegistro dataRegistro = (ParEdicionRegistro) request.getAttribute("dataRegistro");
						%>
						<div class="registro-item">
							Fecha de registro: <%=dataRegistro.getFechaRegistro()%>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>

			<%
			} else if ("organizador".equals(session.getAttribute("tipoUsr"))
					&& Boolean.TRUE.equals(request.getAttribute("organizaEdicion"))) {
				List<String> dataRegistros = (List<String>) request.getAttribute("dataRegistros");
			%>
			<%
			if (dataRegistros != null && !dataRegistros.isEmpty()) {
			%>
			<h2 class="texto-registro" id="listado-titulo">Listado de registros:</h2>
			<div class="contenedor-derecha-alt" id="listado-registros">
				<div class="informacion-TRegistro">
					<div class="detalles-TRegistro">
						<%
						for (String dataR : dataRegistros) {
						%>
						<div class="registro-item"><%=dataR%></div>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<%
			}
			%>
			<%
			}
			%>
		</div>

	</div>

	<%
	}
	%>

</body>
</html>
