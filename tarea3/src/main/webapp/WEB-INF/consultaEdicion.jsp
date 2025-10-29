<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="logica.datatypes.*"%>
<%@ page import="logica.Fabrica"%>
<%@ page import="logica.interfaces.*"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>


<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Consulta Edición :: Mis Eventos</title>

<%
	DataEdicion dataEd = (DataEdicion) request.getAttribute("dataEdicion");
	DataOrganizador dataOrg = (DataOrganizador) request.getAttribute("dataOrganizador");
	List<DataTRegistro> dataTRegistros = (List<DataTRegistro>) request.getAttribute("dataTRegistros");
	List<DataPatrocinioCompleto> dataPatrocinios = (List<DataPatrocinioCompleto>) request.getAttribute("dataPatrocinios");
%>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous" />

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/consultaEdicionStyle.css">
</head>

<body>
	<jsp:include page="/WEB-INF/template/topbar.jsp" />

	<!-- Card Evento -->
	<%
		if (dataOrg != null && dataEd != null) {
			String nomNormal = nombreUtils.normalizarNombre(dataEd.getNombre());
			String nomNormal1 = nombreUtils.normalizarNombre(dataOrg.getNickname());
		%>
	<div class="info-container">
		<a href="" class="event-card">
			<div class="event-card-image">
				<img
					src="${pageContext.request.contextPath}/resources/images/ED-<%= nomNormal %>.png"
					alt="logoEdición" width="150px" height="150px"
					onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
			</div>


			<div class="event-card-content">
				<h3 class="event-card-title"><%=dataEd.getNombre()%></h3>
				<p class="event-card-description"><%=dataEd.getPais()%></p>
				<p class="event-card-description">
					Desde:
					<%=dataEd.getFechaIni()%>
					- Hasta:
					<%=dataEd.getFechaFin()%></p>
				<p class="event-card-description">
					Alta:
					<%=dataEd.getFechaAlta()%></p>
				<p class="event-card-description">
					SIG:
					<%=dataEd.getSigla()%></p>
			</div>
		</a>

		<!-- Mi Registro (Si Aplica) -->

		<%
			if ((request.getAttribute("registrado").equals(true))) {
			%>
		<div class="mi-registro-section" id="miRegistro">
			<%
                		IUsuario ICU = Fabrica.getInstance().getIControladorUsuario();
						DataUsuario dataU = (DataUsuario) session.getAttribute("datosUsr");
						Boolean registrado = (Boolean) request.getAttribute("registrado");
						if (registrado != null && registrado) {
							ParEdicionRegistro dataRegistro = (ParEdicionRegistro) request.getAttribute("dataRegistro");
                        	DataDetalleRegistro detalleReg = ICU.getDetallesRegistro(dataU.getNickname(), dataRegistro);
						%>
			<h3 class="registro-titulo">Mi Registro</h3>
			<div class="mi-registro-card">
				<div class="registro-dato">
					<span class="registro-label">Fecha de registro:</span> <span
						class="registro-valor"><%= detalleReg.getFecha() %></span>
				</div>
				<div class="registro-dato">
					<span class="registro-label">Tipo de registro:</span> <span
						class="registro-valor"><%= detalleReg.getNombreTR() %></span>
				</div>
			</div>
		</div>

		<% 
			} 
		%>

		<% 
			} 
		%>


		<!-- Tipos de Registro -->
		<div class="tipos-registro-section">
			<h3 class="tipos-titulo">Tipos de Registro</h3>

			<%
			if (dataTRegistros != null && !dataTRegistros.isEmpty()) {
			%>

			<div class="tipos-container">
				<%
			for (DataTRegistro dataTR : dataTRegistros) {
			%>
				<div class="tipo-registro-card">
					<div class="tipo-header">
						<h4 class="tipo-nombre"><%=dataTR.getNombre()%></h4>
						<span class="tipo-precio">$<%=dataTR.getCosto() %></span>
					</div>
					<p class="tipo-descripcion"><%=dataTR.getDescr() %></p>
					<div class="tipo-cupo">
						<i class="bi bi-people-fill"></i> <span>Cupo: <%= dataTR.getCupo()%></span>
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



		<!-- Organizador -->
		<div class="organizador-section">
			<h3 class="organizador-titulo">Organiza</h3>

			<div class="organizador-card">
				<div class="organizador-avatar">
					<img
						src="<%=request.getContextPath()%>/resources/images/USR-<%=nomNormal1%>.png"
						alt="logoEdición" width="120px" height="120px"
						onerror="this.onerror=null; this.src='<%=request.getContextPath()%>/resources/images/IMG-NO.png';">
				</div>

				<div class="organizador-info">
					<h4 class="organizador-nombre"><%=dataOrg.getNombre()%></h4>
					<span class="organizador-email"><%=dataOrg.getEmail()%></span>
				</div>
			</div>
		</div>


		<%
		if (dataPatrocinios != null && !dataPatrocinios.isEmpty()) {
		%>
		<!-- Patrocinios -->
		<div class="patrocinios-section">
			<h3 class="patrocinios-titulo">Patrocinios</h3>

			<div class="patrocinios-container">
				<!-- Patrocinio 1 -->
				<%
				for (DataPatrocinioCompleto dataP : dataPatrocinios) {
				%>
				<div class="patrocinio-card">
					<div class="patrocinio-header">
						<h4 class="patrocinio-nombre"><%=dataP.getInstitucion()%></h4>
						<div class="patrocinio-badges">
							<span class="patrocinio-tipo">Nivel: <%=dataP.getNivel()%>.
							</span> <span class="patrocinio-nivel nivel-platino">Código: <%=dataP.getCod()%>..
							</span>
						</div>
					</div>

					<div class="patrocinio-detalles">
						<div class="patrocinio-detalle">
							<span class="detalle-label">Aporte: $<%=dataP.getMonto()%></span>
							<span class="detalle-valor">Cupos: <%=dataP.getCtdCupo()%>.
							</span>
						</div>
					</div>
				</div>
				<%
			}
			%>
			</div>
		</div>
		<%
			}
			%>
	</div>

	<!-- Lowbar -->
	<nav class="bottom-nav">
		<div class="container-fluid">
			<div class="bottom-nav-content">
				<!-- Botón Home -->
				<a href="../listarEventos/listarEventos.html" class="nav-item">
					<i class="bi bi-house-door-fill"></i>
				</a>

				<!-- Botón Perfil con imagen del usuario -->
				<a href="../consultaUsr/consultaUsr.html"
					class="nav-item profile-link">
					<div class="profile-pic">
						<img src="../src/images/IMG-US01.jpg" alt="Perfil" />
					</div>
				</a>
			</div>
		</div>
	</nav>
	<%
			}
			%>
	<!-- LOWBAR -->
	<jsp:include page="/WEB-INF/template/lowbar.jsp" />
</body>
</html>
