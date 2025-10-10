<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.*" %>
<%@ page import="excepciones.*" %>
<%@ page import="logica.Fabrica" %>
<%@ page import="logica.interfaces.IUsuario" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de Usuarios :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

   <style type="text/css">body {
    font-family: outfit;
    margin: 0;
    background: linear-gradient(135deg, #4397db, #dbb5ec);
    min-height: 100vh;
}

.content {
    display: flex;
    justify-content: center;
    width: 100%;
}

.contenedores {
    max-width: 1000px;
    margin: 0 auto;
    margin-top: 60px;
}

.seccion-titulo {
    font-size: 28px;
    margin: 0 0 25px 0;
    color: white;
    padding-bottom: 10px;
    border-bottom: 2px solid rgba(255, 255, 255, 0.3);
}

.contenedor-usuarios {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
    margin-bottom: 40px;
    width: 100%;
}

.tarjeta-usuario {
    background-color: white;
    text-decoration: none;
    border-radius: 20px;
    overflow: hidden;
    transition: all 0.3s ease;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
    cursor: pointer;
    position: relative;
}

.tarjeta-usuario:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
}

.tarjeta-usuario.organizador {
    border-top: 5px solid #9face6;
}

.tarjeta-usuario.asistente {
    border-top: 5px solid #74ebd5;
}

.tarjeta-usuario a {
    display: block;
    text-decoration: none;
    color: inherit;
    background-color: white;
    border-radius: 20px;
    overflow: hidden;
    transition: all 0.3s ease;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
    cursor: pointer;
    position: relative;
}

.imagen-usuario {
    width: 100%;
    height: 180px;
    object-fit: cover;
    background-color: #e0e0e0;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #555;
}

.contenido-usuario {
    padding: 20px;
}

.tipo-usuario {
    position: absolute;
    top: 15px;
    right: 15px;
    padding: 5px 12px;
    border-radius: 15px;
    font-size: 12px;
    font-weight: 600;
    color: white;
}

.tipo-organizador {
    background-color: #9face6;
}

.tipo-asistente {
    background-color: #74ebd5;
}

.nombre-usuario {
    margin: 0 0 10px 0;
    font-size: 22px;
    color: #4397db;
}

.nickname-usuario {
    margin: 0 0 15px 0;
    font-size: 16px;
    color: #555;
}

.detalles-usuario {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.detalle-item {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 14px;
    color: #555;
}

.icono {
    font-size: 16px;
    width: 20px;
    text-align: center;
}

.usuario-actual {
    box-shadow: 0 0 0 3px #ffcc00; /* borde dorado */
}

.etiqueta-usuario-actual {
    position: absolute;
    top: 15px;
    left: 15px;
    background-color: #ffcc00;
    color: #333;
    padding: 3px 10px;
    border-radius: 12px;
    font-size: 11px;
    font-weight: bold;
}

.sin-imagen {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #e0e0e0;
    color: #555;
    font-style: italic;
    height: 180px;
}

.filtros {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
    justify-content: center;
}

.filtro-btn {
    padding: 8px 16px;
    border: 2px solid rgba(255, 255, 255, 0.3);
    background-color: transparent;
    color: white;
    border-radius: 20px;
    cursor: pointer;
    transition: all 0.3s ease;
    font-weight: 500;
}

.filtro-btn:hover {
    background-color: rgba(255, 255, 255, 0.1);
}

.filtro-btn.active {
    background-color: white;
    color: #4397db;
    font-weight: bold;
}</style>
</head>

<body>
    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

    <!-- SIDEBAR -->
    <jsp:include page="/WEB-INF/template/sidebar.jsp" />

    <!-- Contenido -->
    <div class="content">
        <div class="contenedores">
            <h1 class="seccion-titulo">Usuarios Registrados</h1>
            
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null) {
            %>
                <div class="mensaje-info">
                    <p><%= mensaje %></p>
                </div>
            <%
                } else {
                    DataUsuario[] usuarios = (DataUsuario[]) request.getAttribute("usuarios");
                    IUsuario controlUsr = Fabrica.getInstance().getIControladorUsuario();
                    
                    if (usuarios != null && usuarios.length > 0) {
            %>
                        <div class="contenedor-usuarios">
            <%
                        for (DataUsuario usuario : usuarios) {
                            String tipoUsuario = "Usuario";
                            String claseTipo = "tipo-usuario";
                            String claseTarjeta = "";
                            
                            try {
                                tipoUsuario = controlUsr.getTipoUsuario(usuario.getEmail());
                                
                                if ("Asistente".equals(tipoUsuario)) {
                                    claseTipo = "tipo-asistente";
                                    claseTarjeta = "asistente";
                                } else if ("Organizador".equals(tipoUsuario)) {
                                    claseTipo = "tipo-organizador";
                                    claseTarjeta = "organizador";
                                }
                            } catch (UsuarioNoExisteException e) {
                                // Mantener valores por defecto
                            }
                            
                            String detallesAdicionales = "";
                            
                            try {
                                if ("Asistente".equals(tipoUsuario)) {
                                    DataAsistente asistente = controlUsr.getAsistente(usuario.getEmail());
                                    detallesAdicionales = String.format(
                                        "<div class='detalle-item'>" +
                                        "<span class='icono'>🎂</span>" +
                                        "<span>%s</span>" +
                                        "</div>",
                                        asistente.getFechaNac()
                                    );
                                } else if ("Organizador".equals(tipoUsuario)) {
                                    DataOrganizador organizador = controlUsr.getOrganizador(usuario.getEmail());
                                    if (organizador.getDescripcion() != null && !organizador.getDescripcion().isEmpty()) {
                                        detallesAdicionales = String.format(
                                            "<div class='detalle-item'>" +
                                            "<span class='icono'>📋</span>" +
                                            "<span>%s</span>" +
                                            "</div>",
                                            organizador.getDescripcion()
                                        );
                                    }
                                    if (organizador.getUrl() != null && !organizador.getUrl().isEmpty()) {
                                        detallesAdicionales += String.format(
                                            "<div class='detalle-item'>" +
                                            "<span class='icono'>🌐</span>" +
                                            "<span>%s</span>" +
                                            "</div>",
                                            organizador.getUrl()
                                        );
                                    }
                                }
                            } catch (UsuarioNoExisteException e) {
                                // Ignorar error
                            }
                            
                            boolean esUsuarioActual = false;
                            DataUsuario datosU = (DataUsuario) session.getAttribute("datosUsr");
                            String loggedMail = "";
                            if (datosU != null)
                            	loggedMail = datosU.getEmail();
                            if(usuario.getEmail().equals(loggedMail)){
                            	esUsuarioActual = true;
                            }
            %>
                            <a class="tarjeta-usuario <%= claseTarjeta %> <%= esUsuarioActual ? "usuario-actual" : "" %>" 
							   href="${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(usuario.getEmail(), "UTF-8") %>">
							    
							    <% if (esUsuarioActual) { %>
							        <div class="etiqueta-usuario-actual">Tú</div>
							    <% } %>
							    
							    <div class="imagen-usuario">
							        <img src="${pageContext.request.contextPath}/resources/images/usuarios/<%= usuario.getNickname() %>.jpg" 
							             alt="<%= usuario.getNombre() %>" 
							             onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">
							        <div class="sin-imagen" style="display:none;">Sin imagen</div>
							    </div>
							    
							    <span class="tipo-usuario <%= claseTipo %>"><%= tipoUsuario %></span>
							    
							    <div class="contenido-usuario">
							        <h3 class="nombre-usuario"><%= usuario.getNombre() %></h3>
							        <p class="nickname-usuario">@<%= usuario.getNickname() %></p>
							        <div class="detalles-usuario">
							            <div class="detalle-item">
							                <span class="icono">📧</span>
							                <span><%= usuario.getEmail() %></span>
							            </div>
							            <%= detallesAdicionales %>
							        </div>
							    </div>
							</a>
            <%
                        }
            %>
                        </div>
            <%
                    } else {
            %>
                        <p style="color: white; text-align: center;">No se encontraron usuarios.</p>
            <%
                    }
                }
            %>
        </div>
    </div>
</body>

</html>