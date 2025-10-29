<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="excepciones.*" %>
<%@ page import="cliente.ws.usuarios.DataUsuario" %>
<%@ page import="cliente.ws.usuarios.*" %>
<%@ page import="com.miseventos.utils.nombreUtils" %>

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
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/listarUsuariosStyle.css">
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
                    IControladorUsuarioWS ICU_WS;
                    ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService();
                    ICU_WS = servicio2.getControladorUsuarioWSPort();
                    
                    if (usuarios != null && usuarios.length > 0) {
            %>
                        <div class="contenedor-usuarios">
            <%
                        for (DataUsuario usuario : usuarios) {
                            String tipoUsuario = "Usuario";
                            String claseTipo = "tipo-usuario";
                            String claseTarjeta = "";
                            
                            try {
                                tipoUsuario = ICU_WS.getTipoUsuario(usuario.getEmail());
                                
                                if ("Asistente".equals(tipoUsuario)) {
                                    claseTipo = "tipo-asistente";
                                    claseTarjeta = "asistente";
                                } else if ("Organizador".equals(tipoUsuario)) {
                                    claseTipo = "tipo-organizador";
                                    claseTarjeta = "organizador";
                                }
                            } catch (Exception e) {
                                // Mantener valores por defecto
                            }
                            
                            String detallesAdicionales = "";
                            
                            try {
                                if ("Asistente".equals(tipoUsuario)) {
                                    DataAsistente asistente = ICU_WS.getAsistente(usuario.getEmail());
                                    detallesAdicionales = String.format(
                                        "<div class='detalle-item'>" +
                                        "<span class='icono'>🎂</span>" +
                                        "<span>%s</span>" +
                                        "</div>",
                                        asistente.getFechaNac()
                                    );
                                } else if ("Organizador".equals(tipoUsuario)) {
                                    DataOrganizador organizador = ICU_WS.getOrganizador(usuario.getEmail());
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
                            } catch (Exception e) {
                            	System.err.println("⚠️ Error obteniendo detalles adicionales para el usuario " + usuario.getEmail() + " (" + tipoUsuario + "): " + e.getMessage());
                            	    e.printStackTrace();
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
							    
							    <% 
							        if (esUsuarioActual) { 
							    %>
							        <div class="etiqueta-usuario-actual">Tú</div>
							    <%
							        } 
							        String nomNormal = nombreUtils.normalizarNombre(usuario.getNickname());
							    %>
							    
							    <div class="imagen-usuario">
							        <img src="${pageContext.request.contextPath}/resources/images/USR-<%= nomNormal %>.png" 
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