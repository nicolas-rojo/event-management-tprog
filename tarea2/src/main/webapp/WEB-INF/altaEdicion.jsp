<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nueva Edición :: Mis Eventos</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/styles/altaEdicionStyle.css">
</head>

<body>
<a href="<%= request.getContextPath() %>/home">
    <img src="<%= request.getContextPath() %>/resources/images/logo.png" alt="logo" class="logo">
</a>

<div class="login-container">
    <h2>Nueva Edición</h2>

    <%
        String evento = (String) request.getAttribute("evento");
        String error = (String) request.getAttribute("error");
        if (evento != null && !evento.isEmpty()) {
    %>
        <h3>Evento: <%= evento %></h3>
    <%
        }
        if (error != null && !error.isEmpty()) {
    %>
        <div style="color: red; margin-bottom: 10px;"><%= error %></div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/AltaEdicion" method="post" id="formEdicion" enctype="multipart/form-data">
        <input type="hidden" name="evento" value="<%= evento != null ? evento : "" %>">

        <input type="text" id="nombreEd" name="nombreEd" placeholder="Nombre">
        <div class="error-message" id="errorNombreEd">El nombre es requerido</div>

        <input type="text" id="sigla" name="sigla" placeholder="Siglas">
        <div class="error-message" id="errorSigla">La sigla es requerida</div>

        <input type="text" id="ciudad" name="ciudad" placeholder="Ciudad">
        <div class="error-message" id="errorCiudad">La ciudad es requerida</div>

        <input type="text" id="pais" name="pais" placeholder="País">
        <div class="error-message" id="errorPais">El país es requerido</div>

        <label for="fechaIni">Fecha de Inicio:</label>
        <input type="date" id="fechaIni" name="fechaIni">
        <div class="error-message" id="errorFechaIni">La fecha de inicio es requerida</div>

        <label for="fechaFin">Fecha de Fin:</label>
        <input type="date" id="fechaFin" name="fechaFin">
        <div class="error-message" id="errorFechaFin">La fecha de fin es requerida</div>

        <input type="file" id="imagen" name="imagen" accept="image/*">
        
        <input type="text" id="videoUrl" name="videoUrl" placeholder="YouTube URL">

        <div id="confirmarError" style="color: red;"></div>

        <div class="button-group">
            <button type="submit">Aceptar</button>
			<button type="button" id="cancelarBtn" onclick="window.location.href='${pageContext.request.contextPath}/home'">Cancelar</button>
        </div>
    </form>
</div>

<script src="<%= request.getContextPath() %>/resources/scripts/altaEdicionScript.js"></script>
</body>
</html>
