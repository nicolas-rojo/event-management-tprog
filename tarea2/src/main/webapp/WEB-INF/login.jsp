<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login :: Mis Eventos</title>
	
	<!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/loginStyle.css">
</head>

<body>
	<a href="${pageContext.request.contextPath}/home"> 
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
	</a>
	
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form action="${pageContext.request.contextPath}/login" method="post" id="formLogin">
            <input type="text" id="nickmail" name="nickmail" placeholder="Nickname / Mail">
            <div class="error-message" id="errorNickmail">El nickname o mail es requerido</div>
            
            <input type="password" id="clave" name="clave" placeholder="Contraseña">
            <div class="error-message" id="errorClave">La contraseña es requerida</div>
            
            <% 
            	String error = (String) request.getAttribute("error");
            	if (error != null) {
            %>
            	<div id="confirmarError" style = "color:red;"><%= error %></div>
            <%
            	}
            %>
            <button type="submit">Ingresar</button>
            <button type="button" onclick="window.location.href=document.referrer;">Cancelar</button>
        </form>
    </div>
    
    <script src="${pageContext.request.contextPath}/resources/scripts/loginScript.js"></script>
</body>
</html>