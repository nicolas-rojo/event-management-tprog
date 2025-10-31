<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>
<%@ page import="com.miseventos.utils.nombreUtils" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">

<%
	String tipo = (String) session.getAttribute("tipoUsr");
	DataUsuario usr = (DataUsuario) session.getAttribute("datosUsr");
%>

<div class="topbar">
	<div class="topbar-logo">
		<a href="${pageContext.request.contextPath}/home">
			<img src="${pageContext.request.contextPath}/resources/images/logo.png"> 
		</a>
	</div>

	<div class="topbar-search">
		<form id="searchForm" action="${pageContext.request.contextPath}/buscar" method="GET">
			<input type="text" 
				   id="searchInput"
				   name="q" 
				   placeholder="Buscar evento, edicion... 🔍"
				   autocomplete="off">
			
			<select id="ordenarSelect" name="ordenar">
				<option value="fecha">Más recientes</option>
				<option value="alfabetico_asc">A-Z</option>
				<option value="alfabetico_desc">Z-A</option>
			</select>
			
			<button type="submit" id="searchButton">Buscar</button>
		</form>
	</div>
	
	<div class="topbar-right">
		<%
			if (tipo == null) {
		%>
			<a href="${pageContext.request.contextPath}/login">Ingresar</a> <!-- REF AL JSP DE LOGIN -->
			<a href="${pageContext.request.contextPath}/register">Registrarse</a> <!-- REF AL JSP DEL REGISTER -->
		
		<%
			} else {
				String nickNormal = nombreUtils.normalizarNombre(usr.getNickname());
				DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
		%>		
			
			<a href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a> <!-- REF AL SERVLET DE LOGOUT -->
			<a href="${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(datosU.getEmail(), "UTF-8") %>"> <!-- REF A LA CONSULTA DEL PERFIL DEL USUARIO ACTUAL -->
				<img src="${pageContext.request.contextPath}/resources/images/USR-<%= nickNormal %>.png" 
					alt="logoUsr" 
					onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/stockusr.png';"> <!-- IMG DEL USUARIO ACTUAL -->
			</a>
		
		<%
			}
		%>
	</div>
</div>