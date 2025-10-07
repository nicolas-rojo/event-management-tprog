<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">

<%
	String tipo = (String) session.getAttribute("tipoUsr");
%>

<div class="sidebar">	
	<%
		if ("organizador".equals(tipo)) {
	%>
		
		<a href="" id="miPerfil" class="elementTitle" style="display: flex;">Mi Perfil</a> <!-- REF AL JSP DE CONSULTA DE PERFIL -->
		<a href="${pageContext.request.contextPath}/altaEvento" class="element">Alta Evento</a> <!-- REF AL JSP DE ALTA EVENTO -->
		<a href="" class="element">Alta Institución</a> <!-- REF AL JSP DE ALTA DE INSTITUCION -->
		<a href="" class="element">Nuevo Tipo de Registro</a> <!-- REF AL JSP DEL NUEVO TREG -->
		<a href="" class="element">Nuevo Patrocinio</a> <!-- REF AL JSP DE NUEVO PATROCINIO -->

	<%
		} else if ("asistente".equals(tipo)) {
	%>	
		
		<a href="" id="miPerfil" class="elementTitle" style="display: flex;">Mi Perfil</a> <!-- REF AL JSP DE CONSULTA DE PERFIL -->
		<a href="" class="element">Ver Registros</a> <!-- REF AL JSP DE CONSULTA DE PERFIL -->
		
	<%
		}
	%>
	
	<a href="${pageContext.request.contextPath}/home" class="element">Consultar Evento</a>
	<a href="" class="element">Consultar Usuario</a> <!-- REF A JSP DE LISTADO DE USUARIOS -->
</div>