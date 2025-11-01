<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Evento :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/altaEventoStyle.css">
</head>

<body>
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">
    </a>
    <div class="login-container">
        <h2>Nuevo Evento</h2>
        <form action="${pageContext.request.contextPath}/altaEvento" method="post" id="formEvento" enctype="multipart/form-data">
            <input type="text" id="nombreEv" name="nombreEv" placeholder="Nombre" value="<%= request.getAttribute("nombreEv") != null ? request.getAttribute("nombreEv") : "" %>">
            <div class="error-message" id="errorNombreEv">El nombre es requerido</div>

            <textarea id="desc" name="desc" placeholder="Descripción"><%= request.getAttribute("desc") != null ? request.getAttribute("desc") : "" %></textarea>
            <div class="error-message" id="errorDesc">La descripción es requerida</div>

            <input type="text" id="sigla" name="sigla" placeholder="Siglas" value="<%= request.getAttribute("sigla") != null ? request.getAttribute("sigla") : "" %>">
            <div class="error-message" id="errorSigla">Las siglas son requeridas</div>

            <div class="form-group">
                <label for="categorias">Categorías:</label>
                <p1>Mantén Ctrl/Cmd Para Selección Múltiple</p1>
                <select id="categorias" name="categorias" multiple size="6">
                    <option value="Tecnologia">Tecnología</option>
                    <option value="Innovacion">Innovación</option>
                    <option value="Literatura">Literatura</option>
                    <option value="Cultura">Cultura</option>
                    <option value="Musica">Música</option>
                    <option value="Deporte">Deporte</option>
                    <option value="Salud">Salud</option>
                    <option value="Entretenimiento">Entretenimiento</option>
                    <option value="Agro">Agro</option>
                    <option value="Negocios">Negocios</option>
                    <option value="Moda">Moda</option>
                    <option value="Investigacion">Investigación</option>
                </select>
                <div class="error-message" id="errorCategorias">Selecciona al menos una categoría</div>
            </div>

            <input type="file" id="imagen" name="imagen" accept="image/*">

            <!-- Inputs ocultos para las categorías seleccionadas -->
            <div id="categoriasHidden"></div>
            
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
				<button type="button" id="cancelarBtn" onclick="window.location.href='${pageContext.request.contextPath}/home'">Cancelar</button>
            </div>
        </form>
    </div>
   
    <script src="${pageContext.request.contextPath}/resources/scripts/altaEventoScript.js"></script>
</body>

</html>