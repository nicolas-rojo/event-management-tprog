<%@ page import="logica.datatypes.*"%>
<!DOCTYPE html>
<html lang="es">

<% 
DataTRegistro dataTR = (DataTRegistro) request.getAttribute("dataTR");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Registro :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaRegistro.css">
</head>

<body>
    <a href="${pageContext.request.contextPath}/home"> 
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
	</a>
	
    <div class="login-container">
        <h2>Nuevo Registro</h2>
        <form id="formTReg" action="${pageContext.request.contextPath}/altaRegistro" method="post">
	        <input type="hidden" name="evento" value="<%= request.getParameter("evento") %>">
			<input type="hidden" name="edicion" value="<%= request.getParameter("edicion") %>">
			<input type="hidden" name="treg" value="<%= dataTR.getNombre() %>">
            <div class="divCodigo">
               	<input type="text" id="codigo" placeholder="Codigo">
            	<div id="confirmarError" style = "color:red;"></div>
                <button type="button" id="aplicarBtn">Aplicar</button>
            </div>
				<input type="text" id="costoRegistro" value="<%= dataTR.getCosto() %>" disabled>
				<div class="button-group">
                <button type="submit">Aceptar</button>
                <button type="button" id="cancelarBtn" onclick="window.location.href='/home'">Cancelar</button>
            </div>
            <% if (request.getAttribute("errorYaRegistrado") != null) { %>
            	<div style="color: red; font-size: 12px;"><%= request.getAttribute("errorYaRegistrado") %></div>
            <% }else if (request.getAttribute("errorCupo") != null){ %>
            	<div style="color: red; font-size: 12px;"><%= request.getAttribute("errorCupo") %></div>
            <% } %>
        </form>
    </div>
    <script src="../altaReg/scriptCodigo.js"></script>
</body>

</html>