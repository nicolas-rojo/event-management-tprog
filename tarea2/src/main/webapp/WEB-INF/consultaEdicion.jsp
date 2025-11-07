<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- FALTA AREGLARRR -->
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="com.miseventos.utils.nombreUtils" %>
<%@ page import="com.miseventos.utils.fabricaWS" %>

<%@ page import="cliente.ws.eventos.ControladorEventoWSService" %>
<%@ page import="cliente.ws.eventos.IControladorEventoWS" %>
<%@ page import="cliente.ws.usuarios.ControladorUsuarioWSService" %>
<%@ page import="cliente.ws.usuarios.IControladorUsuarioWS" %>
<%@ page import="cliente.ws.eventos.DataEdicion" %>
<%@ page import="cliente.ws.usuarios.DataUsuario" %>
<%@ page import="cliente.ws.usuarios.DataOrganizador" %>
<%@ page import="cliente.ws.usuarios.DataDetalleRegistro" %>
<%@ page import="cliente.ws.usuarios.DataAsistente" %>
<%@ page import="cliente.ws.instituciones.DataPatrocinio" %>
<%@ page import="cliente.ws.eventos.DataPatrocinioCompleto" %>
<%@ page import="cliente.ws.eventos.DataTRegistro" %>
<%@ page import="cliente.ws.usuarios.ParEdicionRegistro" %>

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
							<span class="descripcion-TRegistro"><%=dataTR.getDescripcion()%>.</span>
							<span class="costo-TRegistro">Costo: $<%=dataTR.getCosto()%>.</span>
							<span class="Cupos-TRegistro"><%=dataTR.getCupo()%> cupos restantes.</span>
							<%
							if ("asistente".equals(session.getAttribute("tipoUsr")) && (request.getAttribute("registrado").equals(false))) {
							%>
							<div id="botondiv1" class="boton-div" style="display: flex;">
								<button type="button" class="btn-inscribirse"
									<% 
									String eventoEncoded = java.net.URLEncoder.encode((String) request.getParameter("evento"), "UTF-8");
									String edicionEncoded = java.net.URLEncoder.encode(dataEd.getNombre(), "UTF-8");
									String nombreTREncoded = java.net.URLEncoder.encode(dataTR.getNombre(), "UTF-8");															
									%>
									onclick="window.location.href='<%= request.getContextPath() %>/altaRegistro?evento=<%= eventoEncoded %>&edicion=<%= edicionEncoded %>&treg=<%= nombreTREncoded %>'">
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
			
			<% if (dataEd.getVideoUrl() != null && !dataEd.getVideoUrl().isEmpty()) { %>
			    <h2 class="texto-og">Video:</h2>
			    <div class="contenedor-derecha-video">
			        <iframe class="video-embed"
			                src="<%= dataEd.getVideoUrl() %>" 
			                frameborder="0" 
			                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
			                allowfullscreen>
			        </iframe>
			    </div>
			    <% } %>
		
			<h2 class="texto-og">Organiza:</h2>

			<a href='<%= request.getContextPath() %>/detalleUsuario?email=<%= java.net.URLEncoder.encode(dataOrg.getEmail(), "UTF-8") %>'
               class="link-sin-estilo">
				<div class="contenedor-derecha">
					<img class="imagenes"
						src="<%= request.getContextPath() %>/resources/images/USR-<%= nomNormal1 %>.png"										
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
						//Pido el controlador (su interfaz)
						IControladorUsuarioWS ICU_WS;
						ICU_WS = fabricaWS.getControladorUsuarioWS();
				        
						DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");
						Boolean registrado = (Boolean) request.getAttribute("registrado");
						Boolean asistio = (Boolean) ICU_WS.verificarAsistencia(dataEd.getNombre(), dataU.getNickname());
						if (registrado != null && registrado) {
							ParEdicionRegistro dataRegistro = (ParEdicionRegistro) request.getAttribute("dataRegistro");
                        	DataDetalleRegistro detalleReg = ICU_WS.getDetallesRegistro(dataU.getNickname(), dataRegistro);
						%>
						<div class="registro-item">
							<span>Registrado el: <%= detalleReg.getFecha() %>, como: <%= detalleReg.getNombreTR() %></span>
							<% if (asistio){ %>
								<button  id="descargar" class="Btn" onclick="window.location.href='<%= request.getContextPath() %>/ComprobantePDF?edicion=<%= dataEd.getNombre() %>&ciudad=<%= dataEd.getCiudad() %>&fecha=<%= detalleReg.getFecha() %>&evento=<%= request.getParameter("evento") %>'">
								  <svg class="svgIcon" viewBox="0 0 384 512" height="10px" xmlns="http://www.w3.org/2000/svg">
								    <path d="M169.4 470.6c12.5 12.5 32.8 12.5 45.3 0l160-160c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L224 370.8 224 64c0-17.7-14.3-32-32-32s-32 14.3-32 32l0 306.7L54.6 265.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l160 160z"></path>
								  </svg>
								  <span class="icon2" height="10px"></span>
								</button>
							<% } %>
							
						</div>
						<%
						} else {
						%>
						<div class="registro-item">
							<span>Registrado (detalles no disponibles)</span>
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
