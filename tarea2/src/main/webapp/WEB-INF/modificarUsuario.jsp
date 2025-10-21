<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
        String tipo = (String) request.getAttribute("tipo");
        DataUsuario usuario = (DataUsuario) request.getAttribute("usuario");
        String nombreUsuario = usuario != null ? usuario.getNombre() : "Usuario";
    %>
    <title>Editar Perfil - <%= nombreUsuario %> :: Mis Eventos</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/styles/modificarUsuarioStyle.css">
</head>

<body>
    <a href="<%= request.getContextPath() %>/detalleUsuario?email=<%= usuario.getEmail() %>">
        <img src="<%= request.getContextPath() %>/resources/images/logo.png" alt="logo" class="logo">
    </a>

    <div class="login-container">
        <h2>Editar Perfil</h2>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null && !error.isEmpty()) {
        %>
            <div style="color: red; margin-bottom: 10px;"><%= error %></div>
        <%
            }

            if (usuario != null && tipo != null) {
        %>

        <form action="<%= request.getContextPath() %>/ModificarUsuario" method="post" id="formModificar">
            <input type="hidden" name="tipoUsuario" value="<%= tipo %>">

            <!-- Campos no editables -->
            <div class="campo-no-editable">
                <label>Email:</label>
                <input type="text" value="<%= usuario.getEmail() %>" disabled>
            </div>

            <div class="campo-no-editable">
                <label>Nickname:</label>
                <input type="text" value="<%= usuario.getNickname() %>" disabled>
            </div>

            <%
			    if ("Asistente".equals(tipo)) {
			        DataAsistente asistente = (DataAsistente) usuario;
			%>
			        <!-- Campos editables para Asistente -->
			        <div class="form-group">
			            <label for="nombre">Nombre:</label>
			            <input type="text" id="nombre" name="nombre" 
			                   placeholder="Nombre" 
			                   value="<%= asistente.getNombre() %>" 
			                   required>
			            <div class="error-message" id="errorNombre">El nombre es requerido</div>
			        </div>
			
			        <div class="form-group">
			            <label for="apellido">Apellido:</label>
			            <input type="text" id="apellido" name="apellido" 
			                   placeholder="Apellido" 
			                   value="<%= asistente.getApellido() %>" 
			                   required>
			            <div class="error-message" id="errorApellido">El apellido es requerido</div>
			        </div>
			
			        <!-- Fecha de nacimiento no editable -->
			        <div class="campo-no-editable">
			            <label>Fecha de Nacimiento:</label>
			            <input type="date" value="<%= asistente.getFechaNac() %>" disabled>
			        </div>
			
			<%
			    } else if ("Organizador".equals(tipo)) {
			        DataOrganizador organizador = (DataOrganizador) usuario;
			%>
			        <!-- Campos editables para Organizador -->
			        <div class="form-group">
			            <label for="nombre">Nombre:</label>
			            <input type="text" id="nombre" name="nombre" 
			                   placeholder="Nombre" 
			                   value="<%= organizador.getNombre() %>" 
			                   required>
			            <div class="error-message" id="errorNombre">El nombre es requerido</div>
			        </div>
			
			        <div class="form-group">
			            <label for="descripcion">Descripción:</label>
			            <textarea id="descripcion" name="descripcion" 
			                      placeholder="Descripción (opcional)"><%= organizador.getDescripcion() != null ? organizador.getDescripcion() : "" %></textarea>
			        </div>
			
			        <div class="form-group">
			            <label for="url">URL:</label>
			            <input type="text" id="url" name="url" 
			                   placeholder="URL (opcional)" 
			                   value="<%= organizador.getUrl() != null ? organizador.getUrl() : "" %>">
			        </div>
			
			<%
			    }
			%>

            <!-- Sección de cambio de contraseña -->
            <div class="password-section">
                <h3>Cambiar Contraseña (opcional)</h3>
                
                <div class="form-group">
                    <label for="passActual">Contraseña Actual:</label>
                    <input type="password" id="passActual" name="passActual" 
                           placeholder="Ingrese su contraseña actual">
                    <div class="error-message" id="errorPassActual">La contraseña actual es requerida</div>
                </div>

                <div class="form-group">
                    <label for="passNueva">Contraseña Nueva:</label>
                    <input type="password" id="passNueva" name="passNueva" 
                           placeholder="Ingrese la nueva contraseña">
                    <div class="error-message" id="errorPassNueva">La contraseña nueva es requerida</div>
                </div>

                <div class="form-group">
                    <label for="passConfirmar">Confirmar Contraseña:</label>
                    <input type="password" id="passConfirmar" name="passConfirmar" 
                           placeholder="Confirme la nueva contraseña">
                    <div class="error-message" id="errorPassConfirmar">Las contraseñas no coinciden</div>
                </div>
            </div>

            <div id="confirmarError" style="color: red;"></div>

            <div class="button-group">
                <button type="submit">Guardar Cambios</button>
                <a href="${pageContext.request.contextPath}/detalleUsuario?email=<%= usuario.getEmail() %>" class="boton-cancelar">Cancelar</a>
            </div>
        </form>

        <%
            }
        %>
    </div>

    <script src="<%= request.getContextPath() %>/resources/scripts/modificarUsuarioScript.js"></script>
</body>
</html>