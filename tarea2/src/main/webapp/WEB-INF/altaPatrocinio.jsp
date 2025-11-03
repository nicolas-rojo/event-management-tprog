<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cliente.ws.instituciones.DataInstitucion" %>
<%@ page import="cliente.ws.instituciones.Nivel" %>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nuevo Patrocinio :: Mis Eventos</title>

<!-- Fuente -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/altaPatrocinioStyle.css">
</head>

<body>
	<a href="${pageContext.request.contextPath}/home"> <img
		src="${pageContext.request.contextPath}/resources/images/logo.png"
		alt="logo" class="logo">
	</a>

	<div class="form-container">
		<h2>Nuevo Patrocinio</h2>

		<!-- Mostrar evento y edición -->
		<% 
			String evento = (String) request.getAttribute("evento");
			String edicion = (String) request.getAttribute("edicion");
			if (evento != null && edicion != null) {
		%>
			<h3>Evento: <%= evento %></h3>
			<h4>Edición: <%= edicion %></h4>
		<% } %>

		<!-- Mensaje de error del servidor -->
		<% if (request.getAttribute("error") != null) { %>
			<div class="server-error"><%= request.getAttribute("error") %></div>
		<% } %>

		<form action="${pageContext.request.contextPath}/AltaPatrocinio"
			method="post" id="formPatrocinio">

			<!-- Campos ocultos para evento y edición -->
			<input type="hidden" name="evento" value="<%= evento != null ? evento : "" %>">
			<input type="hidden" name="edicion" value="<%= edicion != null ? edicion : "" %>">

			<!-- Tipo de Registro -->
			<label for="tipoRegistro">Tipo de Registro:</label>
			<select id="tipoRegistro" name="tipoRegistro">
				<option value="">-- Seleccionar Tipo de Registro --</option>
				<% 
					List<String> tiposRegistro = (List<String>) request.getAttribute("tiposRegistro");
					String tipoRegistroSeleccionado = (String) request.getAttribute("tipoRegistro");
					if (tiposRegistro != null) {
						for (String tr : tiposRegistro) {
							
				%>
					 <option value="<%= tr %>" <%= tr.equals(tipoRegistroSeleccionado) ? "selected" : "" %>><%= tr %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorTipoRegistro">Debe seleccionar un tipo de registro</div>

			<!-- Institución -->
			<label for="institucion">Institución:</label>
			<select id="institucion" name="institucion">
				<option value="">-- Seleccionar Institución --</option>
				<% 
					DataInstitucion[] instituciones = (DataInstitucion[]) request.getAttribute("instituciones");
					String institucionSeleccionada = (String) request.getAttribute("institucion");
					if (instituciones != null) {
						for (DataInstitucion inst : instituciones) {
							String nombre = inst.getNombre();
				%>
					<option value="<%= nombre %>" <%= nombre.equals(institucionSeleccionada) ? "selected" : "" %>><%= nombre %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorInstitucion">Debe seleccionar una institución</div>

			<!-- Nivel de Patrocinio -->
			<label for="nivel">Nivel de Patrocinio:</label>
			<select id="nivel" name="nivel">
				<option value="">-- Seleccionar Nivel --</option>
				<% 
					Nivel[] niveles = (Nivel[]) request.getAttribute("niveles");
					String nivelStr = (String) request.getAttribute("nivelStr");
					if (niveles != null) {
						for (Nivel nivel : niveles) {
							String valor = nivel.toString();
				%>
					<option value="<%= valor %>" <%= valor.equals(nivelStr) ? "selected" : "" %>><%= valor %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorNivel">Debe seleccionar un nivel</div>

			<!-- Aporte Económico -->
			<label for="monto">Aporte Económico:</label>
			<input type="number" id="monto" name="monto" step="0.01" min="0" placeholder="0.00" value="<%= request.getAttribute("monto") != null ? request.getAttribute("monto") : "" %>">
			<div class="error-message" id="errorMonto">El monto es requerido y debe ser positivo</div>

			<!-- Cantidad de Cupos -->
			<label for="cantidadCupos">Cantidad de Cupos Gratuitos:</label>
			<input type="number" id="cantidadCupos" name="cantidadCupos" min="0" placeholder="0" value="<%= request.getAttribute("cantidadCupos") != null ? request.getAttribute("cantidadCupos") : "" %>">
			<div class="error-message" id="errorCantidadCupos">La cantidad de cupos es requerida y debe ser positiva</div>

			<!-- Código de Patrocinio -->
			<label for="codigo">Código de Patrocinio:</label>
			<input type="text" id="codigo" name="codigo" placeholder="Código único" value="<%= request.getAttribute("codigo") != null ? request.getAttribute("codigo") : "" %>">
			<div class="error-message" id="errorCodigo">El código es requerido</div>

			<div id="confirmarError" class="confirm-error"></div>

			<div class="button-group">
				<button type="submit">Aceptar</button>
				<button type="button" id="cancelarBtn" onclick="window.location.href='${pageContext.request.contextPath}/home'">Cancelar</button>
			</div>
		</form>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/scripts/altaPatrocinioScript.js"></script>
</body>
</html>