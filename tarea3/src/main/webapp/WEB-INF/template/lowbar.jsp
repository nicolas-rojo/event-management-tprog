<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>
<%@ page import="logica.datatypes.*" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<%
	DataUsuario usr = (DataUsuario) session.getAttribute("datosUsr");
%>

<nav class="bottom-nav">
	<div class="container-fluid">
    	<div class="bottom-nav-content">
      		<!-- Botón Home -->
			<a href="index.html" class="nav-item">
				<i class="bi bi-house-door-fill"></i>
			</a>

      		<!-- Botón Perfil con imagen del usuario -->
      		<a href="${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(usr.getEmail(), "UTF-8") %>" class="nav-item profile-link">
				<div class="profile-pic">
					<img src="${pageContext.request.contextPath}/resources/images/USR-<%= nombreUtils.normalizarNombre(usr.getNickname()) %>.png" 
						alt="logoUsr" 
						onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/stockusr.png';">
				</div>
      		</a>
    	</div>
  	</div>
</nav>