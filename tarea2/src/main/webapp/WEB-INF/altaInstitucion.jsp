<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nueva Institución :: Mis Eventos</title>

<!-- Fuente -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/altaInstitucionStyle.css">
</head>

<body>
	<a href="${pageContext.request.contextPath}/home"> <img
		src="${pageContext.request.contextPath}/resources/images/logo.png"
		alt="logo" class="logo">
	</a>

	<div class="login-container">
		<h2>Nueva Institución</h2>

		<form action="${pageContext.request.contextPath}/AltaInstitucion"
			method="post" id="formInstitucion">

			<input type="text" id="nombre" name="nombre" placeholder="Nombre" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>">
			<div class="error-message" id="errorNombre">El nombre es
				requerido</div>

			<input type="text" id="sitioWeb" name="sitioWeb" placeholder="Sitio Web" value="<%= request.getAttribute("sitioWeb") != null ? request.getAttribute("sitioWeb") : "" %>">
			<div class="error-message" id="errorSitioWeb">El sitio web es
				requerido</div>

			<textarea id="descripcion" name="descripcion" placeholder="Descripción"><%= request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : "" %></textarea>
			<div class="error-message" id="errorDescripcion">La descripción es requerida</div>

			<div id="confirmarError" style="color: red;"></div>
			
			<!-- Mensaje de error del servidor -->
				<c:if test="${not empty error}">
					<div style="color: red; margin-bottom: 10px;">${error}</div>
				</c:if>
			

			<div class="button-group">
				<button type="submit">Aceptar</button>
				<button type="button" id="cancelarBtn" onclick="window.location.href='${pageContext.request.contextPath}/home'">Cancelar</button>
			</div>
		</form>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/scripts/altaInstitucionScript.js"></script>
</body>
</html>