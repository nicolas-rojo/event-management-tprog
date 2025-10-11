<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Tipo Registro :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaTRegistro.css">
</head>

<body>
    <a href="${pageContext.request.contextPath}/home"> 
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
	</a>
	
    <div class="login-container">
        <h2>Nuevo Tipo de Registro</h2>
        <form id="formTReg" action="${pageContext.request.contextPath}/altatiporegistro" method="post">
            <input type="text" id="nombre" name="nombre" placeholder="Nombre">
            <div class="error-message" id="errorNombre">El nickname es requerido</div>
            
            <textarea id="descripcion" name="descripcion" placeholder="Descripci�n"></textarea>
            <div class="error-message" id="errorDescripcion">La descripcion es requerida</div>
            
            <input type="number" id="costo" name="costo" placeholder="Costo" min="0" step="0.01">
            <div class="error-message" id="errorCosto">El costo es requerido</div>
            <div class="error-message" id="errorCosto" style="color:red; font-size:12px;">
    			<%= request.getAttribute("errorCosto") != null ? request.getAttribute("errorCosto") : "Debe ingresar un cupo mayor a 0" %>
			</div>
            
            <input type="number" id="cupo" name="cupo" placeholder="Cupo">
            <div class="error-message" id="errorCupo">El cupo es requerido</div>
            <div class="error-message" id="errorCosto" style="color:red; font-size:12px;">
    			<%= request.getAttribute("errorCupo") != null ? request.getAttribute("errorCupo") : "Debe ingresar un costo positivo" %>
			</div>
            
            
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
                <button type="button" id="cancelarBtn" onclick="window.location.href=´/home´">Cancelar</button>
            </div>
        </form>
    </div>
    
    <script src="${pageContext.request.contextPath}/resources/scripts/altaTRegistroScript.js"></script>
</body>

</html>