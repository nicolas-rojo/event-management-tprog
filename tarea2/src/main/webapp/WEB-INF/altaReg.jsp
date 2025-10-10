<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaRegistro.css">
</head>

<body>
    <a href="../index/index.html">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo"> 
    </a>
    <div class="login-container">
        <h2>Nueva Registro</h2>
        <form id="formTReg" action="altaregistro" method="post">
            <div class="divCodigo">
                <input type="text" id="codigo" placeholder="Codigo">
                    <div id="confirmarError" style = "color:red;"></div>
                <button type="button" id="aplicarBtn">Aplicar</button>
            </div>
            <input type="text" id="costoRegistro" placeholder="$1000" disabled>
            <div class="button-group">
                <button type="submit">Aceptar</button>
                <button type="button" id="cancelarBtn" onclick="history.back()">Cancelar</button>
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