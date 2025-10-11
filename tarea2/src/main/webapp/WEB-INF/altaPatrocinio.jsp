<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="logica.datatypes.DataInstitucion" %>
<%@ page import="logica.datatypes.Nivel" %>

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

		<!-- Mensaje de error del servidor -->
		<% if (request.getAttribute("error") != null) { %>
			<div class="server-error"><%= request.getAttribute("error") %></div>
		<% } %>

		<form action="${pageContext.request.contextPath}/AltaPatrocinio"
			method="post" id="formPatrocinio">

			<!-- Evento -->
			<label for="evento">Evento:</label>
			<select id="evento" name="evento">
				<option value="">-- Seleccionar Evento --</option>
				<% 
					List<String> eventos = (List<String>) request.getAttribute("eventos");
					String eventoSeleccionado = (String) request.getAttribute("eventoSeleccionado");
					if (eventos != null) {
						for (String evento : eventos) {
				%>
					<option value="<%= evento %>" <%= (eventoSeleccionado != null && eventoSeleccionado.equals(evento)) ? "selected" : "" %>><%= evento %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorEvento">Debe seleccionar un evento</div>

			<!-- Edición -->
			<label for="edicion">Edición:</label>
			<select id="edicion" name="edicion">
				<option value="">-- Seleccionar Edición --</option>
				<% 
					List<String> ediciones = (List<String>) request.getAttribute("ediciones");
					String edicionSeleccionada = (String) request.getAttribute("edicionSeleccionada");
					if (ediciones != null) {
						for (String ed : ediciones) {
				%>
					<option value="<%= ed %>" <%= (edicionSeleccionada != null && edicionSeleccionada.equals(ed)) ? "selected" : "" %>><%= ed %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorEdicion">Debe seleccionar una edición</div>

			<!-- Tipo de Registro -->
			<label for="tipoRegistro">Tipo de Registro:</label>
			<select id="tipoRegistro" name="tipoRegistro">
				<option value="">-- Seleccionar Tipo de Registro --</option>
				<% 
					List<String> tiposRegistro = (List<String>) request.getAttribute("tiposRegistro");
					if (tiposRegistro != null) {
						for (String tr : tiposRegistro) {
				%>
					<option value="<%= tr %>"><%= tr %></option>
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
					if (instituciones != null) {
						for (DataInstitucion inst : instituciones) {
				%>
					<option value="<%= inst.getNombre() %>"><%= inst.getNombre() %></option>
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
					if (niveles != null) {
						for (Nivel nivel : niveles) {
				%>
					<option value="<%= nivel %>"><%= nivel %></option>
				<% 
						}
					}
				%>
			</select>
			<div class="error-message" id="errorNivel">Debe seleccionar un nivel</div>

			<!-- Aporte Económico -->
			<label for="monto">Aporte Económico:</label>
			<input type="number" id="monto" name="monto" step="0.01" min="0" placeholder="0.00">
			<div class="error-message" id="errorMonto">El monto es requerido y debe ser positivo</div>

			<!-- Cantidad de Cupos -->
			<label for="cantidadCupos">Cantidad de Cupos Gratuitos:</label>
			<input type="number" id="cantidadCupos" name="cantidadCupos" min="0" placeholder="0">
			<div class="error-message" id="errorCantidadCupos">La cantidad de cupos es requerida y debe ser positiva</div>

			<!-- Código de Patrocinio -->
			<label for="codigo">Código de Patrocinio:</label>
			<input type="text" id="codigo" name="codigo" placeholder="Código">
			<div class="error-message" id="errorCodigo">El código es requerido</div>

			<div id="confirmarError" class="confirm-error"></div>

			<div class="button-group">
				<button type="submit">Aceptar</button>
				<button type="button" onclick="history.back()">Cancelar</button>
			</div>
		</form>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/scripts/altaPatrocinioScript.js"></script>
</body>
</html>