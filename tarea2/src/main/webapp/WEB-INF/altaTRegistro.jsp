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
        <form id="formTReg" action="${pageContext.request.contextPath}/altaTReg" method="post">
        	<input type="hidden" name="evento" value="<%= request.getParameter("evento") %>">
			<input type="hidden" name="edicion" value="<%= request.getParameter("edicion") %>">
        	
            <input type="text" id="nombre" name="nombre" placeholder="Nombre">
            <div class="error-message" id="errorNombre">El nickname es requerido</div>
            <% if (request.getAttribute("errorExiste") != null) { %>
            	<div style="color: red; font-size: 12px;"><%= request.getAttribute("errorExiste") %></div>
            <% } %>
            
            <textarea id="descripcion" name="descripcion" placeholder="Descripcion"></textarea>
            <div class="error-message" id="errorDescripcion">La descripcion es requerida</div>
            
            <input type="number" id="costo" name="costo" placeholder="Costo" step="0.01">
            <div class="error-message" id="errorCosto" style="color:red; font-size:12px; display:none;"></div>

            
            <input type="number" id="cupo" name="cupo" placeholder="Cupo">
            <div class="error-message" id="errorCupo" style="color:red; font-size:12px; display:none;"></div>
            
            
            
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
    
    <script src="${pageContext.request.contextPath}/resources/scripts/altaTRegistroScript.js"></script>
</body>

</html>