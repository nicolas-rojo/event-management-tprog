<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Edicion :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaEdicionStyle.css">
</head>

<body>
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
    </a>
    <div class="login-container">
        <form action="${pageContext.request.contextPath}/AltaEdicion" method="post" id="formEdicion" enctype="multipart/form-data">
            <input type="text" id="nombreEd" name="nombreEd" placeholder="Nombre">
             <div class="error-message" id="errorNombreEd">El nombre es requerido</div>
            
            <input type="text" id="sigla" name="sigla" placeholder="Siglas">
             <div class="error-message" id="errorSigla">La sigla es requerida</div>
            
            <input type="text" id="ciudad" name="ciudad" placeholder="Ciudad">
             <div class="error-message" id="errorCiudad">La ciudad es requerida</div>
            
            <input type="text" id="pais" name="pais" placeholder="Pais">
             <div class="error-message" id="errorPais">El pais es requerido</div>
            
            <label for="fechaIni">Fecha de Inicio:</label>
            <input type="date" id="fechaIni" name="fechaIni"  placeholder="Fecha de Inicio">
             <div class="error-message" id="errorFechaIni">La fecha de inicio es requerida</div>
                        
            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" placeholder="Fecha de Finalización">
             <div class="error-message" id="errorFechaFin">La fecha de fin es requerida</div>
             <div id="confirmarError" style = "color:red;"></div>
            
            <label for="fotoPerfil">Foto de Edicion:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">
            
            <% 
            	String error = (String) request.getAttribute("error");
            	if (error != null) {
            %>
            	<div id="confirmarError" style = "color:red;"><%= error %></div>
            <%
            	}
            %>
            <div class="button-group">
                <button type="submit">Aceptar</button>
                <button type="button" id="cancelarBtn" onclick="history.back()">Cancelar</button>
            </div>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/resources/scripts/altaEdicionScript.js"></script>
</body>

</html>