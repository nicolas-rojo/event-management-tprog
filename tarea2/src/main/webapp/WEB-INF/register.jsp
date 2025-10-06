<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/registerStyle.css">
</head>

<body>
	<a href="${pageContext.request.contextPath}/home"> 
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
	</a>
	
    <div class="login-container">
        <h2>Registrarse</h2>
        <form id="form" action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="tipoUsr">Tipo de Usuario:</label>
                <select id="tipo" name="tipo">
                    <option value="organizador">Organizador</option>
                    <option value="asistente">Asistente</option>
                </select>
            </div>
            
            <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>
            <input type="text" id="nickname" name="nick" placeholder="Nickname" required>
            <input type="email" id="mail" name="mail" placeholder="Email" required>
            <input type="password" id="password" name="pass" placeholder="Contraseña" required>
            <div id="confirmarError" style = "color:red;"></div>
            <input type="password" id="confirmacion" name="conPass" placeholder="Confirmar Contraseña" required>

            <!-- Campos de Organizador -->
            <div id="Campos-Org" style="display: none;">
                <input type="text" id="url" name="url" placeholder="Link al Sitio Web" required>
                <textarea id="desc" name="desc" placeholder="Descripción" required></textarea>
            </div>
            
            <!-- Campos de Asistente -->
            <div id="Campos-Asist" style="display: none;">
                <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>
                <label for="fechaNac">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNac" name="fechaNac" placeholder="Fecha de Nacimiento" required>
            </div>
            
            <label for="fotoPerfil">Foto de Perfil:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">
            
            <% 
            	String error = (String) request.getAttribute("error");
            	if (error != null) {
            %>
            	<div id="confirmarError" style = "color:red;"><%= error %></div>
            <%
            	}
            %>
            
            <button type="submit">Registrarse</button>
            <button type="button" onclick="window.location.href=document.referrer;">Cancelar</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/resources/scripts/registerScript.js"></script>
</body>
</html>