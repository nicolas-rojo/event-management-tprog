<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cliente.ws.usuarios.*" %>

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
            
            <input type="text" id="nombre" name="nombre" placeholder="Nombre">
            <div class="error-message" id="errorNombre">El nombre es requerido</div>
            
            <input type="text" id="nickname" name="nick" placeholder="Nickname" onkeyup="validarDato('nickname')">
            <div class="error-message" id="nicknameExiste"></div>
            <div class="error-message" id="errorNickname">El nickname es requerido</div>
            
            <input type="email" id="mail" name="mail" placeholder="Email" onkeyup="validarDato('mail')">
            <div class="error-message" id="mailExiste"></div>
            <div class="error-message" id="errorMail">El mail es requerido</div>
            
            <input type="password" id="password" name="pass" placeholder="Contraseña">
            <div class="error-message" id="errorPassword">La contraseña es requerida</div>
            
            <div id="confirmarError" style = "color:red;"></div>
            
            <input type="password" id="confirmacion" name="conPass" placeholder="Confirmar Contraseña">

            <!-- Campos de Organizador -->
            <div id="Campos-Org" style="display: none;">
                <input type="text" id="url" name="url" placeholder="Link al Sitio Web">
                
                <textarea id="desc" name="desc" placeholder="Descripción"></textarea>
            	<div class="error-message" id="errorDesc">La descripcion es requerida</div>
            </div>
            
            <!-- Campos de Asistente -->
            <div id="Campos-Asist" style="display: none;">
                <input type="text" id="apellido" name="apellido" placeholder="Apellido">
                <div class="error-message" id="errorApellido">El apellido es requerido</div>
                
                <label for="fechaNac">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNac" name="fechaNac" placeholder="Fecha de Nacimiento">
				<div class="error-message" id="errorFechaNac">La fecha de nacimiento es requerida</div>            
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
            
            <button type="submit" id = "Registrarse">Registrarse</button>
			<button type="button" id="cancelarBtn" onclick="window.location.href='${pageContext.request.contextPath}/home'">Cancelar</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/resources/scripts/registerScript.js"></script>
</body>
</html>