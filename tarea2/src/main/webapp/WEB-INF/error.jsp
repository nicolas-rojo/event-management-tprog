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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/errorStyle.css">
</head>

<body>
    <a href="../index/index.html">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo"> 
    </a>
    <div class="error-container">
    	<form id="error" action="error" method="post">
    
	        <h2>ERROR</h2>
	        <div style="color:red;">
	        	<%= request.getAttribute("error") != null ? request.getAttribute("error") : "Error desconocido" %>
	    	</div>
	        <button type="submit">Volver al Inicio</button>
	    </form>
    </div>
</body>


</html>