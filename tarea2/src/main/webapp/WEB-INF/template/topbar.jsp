<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">

<%
	String tipo = (String) session.getAttribute("tipoUsr");
%>

<div class="topbar">
	<div class="topbar-logo">
		<a href="${pageContext.request.contextPath}/home">
			<img src="${pageContext.request.contextPath}/resources/images/logo.png"> 
		</a>
	</div>

	<div class="topbar-search">
		<input type="text" placeholder="Buscar evento, edicion... 🔍">
	</div>
	
	<div class="topbar-right">
		<%
			if (tipo == null) {
		%>
			<a href="${pageContext.request.contextPath}/login">Ingresar</a> <!-- REF AL JSP DE LOGIN -->
			<a href="${pageContext.request.contextPath}/register">Registrarse</a> <!-- REF AL JSP DEL REGISTER -->
		
		<%
			} else {
		%>		
			
			<a href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a> <!-- REF AL SERVLET DE LOGOUT -->
			<a href=""> <!-- REF A LA CONSULTA DEL PERFIL DEL USUARIO ACTUAL -->
				<img src="" alt="logoUsr" onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/stockusr.png';"> <!-- IMG DEL USUARIO ACTUAL -->
			</a>
		
		<%
			}
		%>
	</div>
</div>