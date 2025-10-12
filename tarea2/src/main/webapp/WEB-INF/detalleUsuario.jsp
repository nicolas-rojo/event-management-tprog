<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="logica.datatypes.*" %>
<%@ page import="logica.Fabrica" %>
<%@ page import="logica.interfaces.*" %>
<%@ page import="com.miseventos.utils.nombreUtils" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
		DataUsuario usr = (DataUsuario) session.getAttribute("datosUsr");
    	String loggedMail = "";
    	if (usr != null)
    		loggedMail = usr.getEmail();
        String tipo = (String) request.getAttribute("tipo");
        DataUsuario usuario = (DataUsuario) request.getAttribute("usuario");
        String nombreUsuario = usuario != null ? usuario.getNombre() : "Usuario";
    %>
    <title><%= nombreUsuario %> - Detalle de <%= tipo %> :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/detalleUsuarioStyle.css">
</head>

<body class="<%= tipo != null ? tipo.toLowerCase() : "" %>">
    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

    <!-- SIDEBAR -->
    <jsp:include page="/WEB-INF/template/sidebar.jsp" />

    <!-- Contenido -->
    <div class="content">
        <div class="contenedores">
            <!-- Botón para volver -->
            <a href="${pageContext.request.contextPath}/consultaUsuario" class="boton-volver">← Volver a la lista</a>
            
            <%
                if (usuario != null && tipo != null) {
                	String nomNormal = nombreUtils.normalizarNombre(usuario.getNickname());
            %>
            
            <!-- Información principal del usuario -->
            <div class="contenedor-principal">
                <div class="imagen-usuario">
                    <img src="${pageContext.request.contextPath}/resources/images/USR-<%= nomNormal %>.png" 
                         alt="<%= usuario.getNombre() %>" 
                         onerror="this.style.display='none'; this.parentElement.innerHTML='Sin imagen'">
                </div>
                <div class="informacion-usuario">
                    <h1 class="nombre-usuario"><%= usuario.getNombre() %></h1>
                    <p class="nickname-usuario">@<%= usuario.getNickname() %></p>
                    <%if(usuario.getEmail().equals(loggedMail)){%>
                    	<button class="boton-editar-perfil" onclick="alert('Funcionalidad de editar perfil - Por implementar')">✏️ Editar Perfil</button>
                    <%} %>
                    <div class="detalles-usuario">
                        <div class="detalle-item">
                            <span class="icono">📧</span>
                            <span><%= usuario.getEmail() %></span>
                        </div>
                        <div class="detalle-item">
                            <span class="icono">👤</span>
                            <span>Tipo: <%= tipo %></span>
                        </div>
                        
                        <%
                            if ("Asistente".equals(tipo)) {
                                DataAsistente asistente = (DataAsistente) usuario;
                        %>
                                <div class="detalle-item">
                                    <span class="icono">🎂</span>
                                    <span>Fecha de nacimiento: <%= asistente.getFechaNac() %></span>
                                </div>
                                <div class="detalle-item">
                                    <span class="icono">📅</span>
                                    <span>Apellido: <%= asistente.getApellido() %></span>
                                </div>
                        <%
                            } else if ("Organizador".equals(tipo)) {
                                DataOrganizador organizador = (DataOrganizador) usuario;
                                if (organizador.getUrl() != null && !organizador.getUrl().isEmpty()) {
                        %>
                                    <div class="detalle-item">
                                        <span class="icono">🌐</span>
                                        <span><%= organizador.getUrl() %></span>
                                    </div>
                        <%
                                }
                                if (organizador.getDescripcion() != null && !organizador.getDescripcion().isEmpty()) {
                        %>
                                    <div class="detalle-item">
                                        <span class="icono">📝</span>
                                        <span><%= organizador.getDescripcion() %></span>
                                    </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </div>

            <%
                if ("Asistente".equals(tipo)) {
                    @SuppressWarnings("unchecked")
                    List<ParEdicionRegistro> registros = (List<ParEdicionRegistro>) request.getAttribute("registros");
            %>
                    <!-- Sección de registros a eventos -->
                    <div class="seccion-registros">
                        <h2 class="seccion-titulo">Mis Registros a Eventos</h2>
                        <div class="contenedor-secundario">
                            <%
                                if (registros != null && !registros.isEmpty() && usuario.getEmail().equals(loggedMail)) {
                            %>
                                    <div class="lista-items">
                                    <%
                                		IUsuario ICU = Fabrica.getInstance().getIControladorUsuario();
                                        for (ParEdicionRegistro registro : registros) {
                                        	DataDetalleRegistro DataReg = ICU.getDetallesRegistro(usuario.getNickname(), registro);
                                    %>
                                        <div class="item">
                                            <div class="info-registro">
                                                <span class="nombre-evento">
                                                    ✅ <%= registro.getNombreEdicion() %>
                                                </span>
                                                <span class="detalle-registro">
                                                	Tipo: <%= DataReg.getTipoRegistro() %>
                                                </span>
                                                <span class="fecha-costo">
                                                	Registrado: <%= DataReg.getFechaRegistro() %> - Costo: $<%= DataReg.getCosto() %>
                                                </span>
                                            </div>
                                        </div>
                                    <%
                                        }
                                    %>
                                    </div>
                            <%
                                } else {
                            %>
                                    <p style="text-align: center; color: #666; padding: 20px;">
                                        No hay registros disponibles para este asistente.
                                    </p>
                            <%
                                }
                            %>
                        </div>
                    </div>
            <%
                } else if ("Organizador".equals(tipo)) {
                    DataEdicionWeb[] ediciones = (DataEdicionWeb[]) request.getAttribute("ediciones");
            %>
                    <!-- Sección de eventos organizados -->
                    <div class="seccion-eventos">
                        <h2 class="seccion-titulo">Ediciones de Eventos Organizadas</h2>
                        <div class="contenedor-secundario">
                            <%
                            	IEventos IEV = Fabrica.getInstance().getIControladorEventos();
                                if (ediciones != null && ediciones.length > 0) {
                            %>
                                    <div class="lista-items">
                                    <%
                                        for (DataEdicionWeb edicion : ediciones) {
                                        	String evento = IEV.eventoTieneEdicion(edicion.getNombre());
                                        	if(edicion.getEstado() == Estado.Confirmado){
                                    %>
                                        <div class="item">
                                            <div class="info-evento">
                                                <span class="nombre-evento">
                                                    ✅ <a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= java.net.URLEncoder.encode(evento, "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(edicion.getNombre(),"UTF-8") %>"
                                                    class = "nombre-evento-link">
                                                    <%= edicion.getNombre() %> (<%= edicion.getSigla() %>) </a>
                                                </span>
                                                <span class="detalle-evento">
                                                    Estado: Confirmada - <%= edicion.getCiudad() %>, <%= edicion.getPais() %>
                                                </span>
                                                <span class="fecha-evento">
                                                    Fecha: <%= edicion.getFechaIni() %> - <%= edicion.getFechaFin() %> | Ingresada: <%= edicion.getFechaAlta() %>
                                                </span>
                                                <%
                                                if(usuario.getEmail().equals(loggedMail)){
                                                	String edicionEncoded = java.net.URLEncoder.encode(edicion.getNombre(), "UTF-8");
													String nombreEvento = IEV.eventoTieneEdicion(edicion.getNombre());
													String eventoEncoded = java.net.URLEncoder.encode(nombreEvento, "UTF-8");
                                                %>
				                                    <div class="botones-evento">
					                                    <a href="${pageContext.request.contextPath}/altaTReg?evento=<%= eventoEncoded %>&edicion=<%= edicionEncoded %>" class="boton-evento boton-tipo-registro">Nuevo Tipo Registro</a>
														<a href="<%= request.getContextPath() %>/AltaPatrocinio?evento=<%= java.net.URLEncoder.encode(evento, "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(edicion.getNombre(),"UTF-8") %>" class="boton-evento boton-patrocinio">Nuevo Patrocinio</a>														
					                                </div>
                    							<%} %>
                                            </div>
                                        </div>
                                    <%
                                        	 }else{
                                        		 if(usuario.getEmail().equals(loggedMail)){
                                    %>
                                    	<div class="item">
                                            <div class="info-evento">
                                                <span class="nombre-evento">
                                                    ❌ <a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= java.net.URLEncoder.encode(evento, "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(edicion.getNombre(),"UTF-8") %>"
                                                    class = "nombre-evento-link">
                                                    <%= edicion.getNombre() %> (<%= edicion.getSigla() %>) </a>
                                                </span>
                                                <span class="detalle-evento">
                                                    Estado: <%= edicion.getEstado()%> edicion - <%= edicion.getCiudad() %>, <%= edicion.getPais() %>
                                                </span>
                                                <span class="fecha-evento">
                                                    Fecha: <%= edicion.getFechaIni() %> - <%= edicion.getFechaFin() %> | Ingresada: <%= edicion.getFechaAlta() %>
                                                </span>
                                            </div>
                                        </div>  
                                    <%
                                        		 }
                                        	}
                                        }
                                    %>
                                    </div>
                            <%
                                } else {
                            %>
                                    <p style="text-align: center; color: #666; padding: 20px;">
                                        No hay ediciones asociadas a este organizador.
                                    </p>
                            <%
                                }
                            %>
                        </div>
                    </div>
            <%
                }
            %>
            
            <%
                }
            %>
        </div>
    </div>
</body>

</html>