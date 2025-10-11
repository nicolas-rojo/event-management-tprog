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

    <style type="text/css">* {
    box-sizing: border-box;
}

body {
    font-family: 'Outfit', sans-serif;
    margin: 0;
    background: linear-gradient(135deg, #4397db, #dbb5ec);
    min-height: 100vh;
    overflow-x: hidden;
}

.content {
    display: flex;
    padding: 20px;
    justify-content: center;
    width: 100%;
    box-sizing: border-box;
}

.contenedores {
    max-width: 800px;
    width: 100%;
    margin: 0 auto;
    margin-top: 60px;
}

/* Botón para volver */
.boton-volver {
	display: block;
	width: fit-content;
    background-color: rgba(255, 255, 255, 0.2);
    color: white;
    border: 2px solid rgba(255, 255, 255, 0.3);
    padding: 10px 20px;
    border-radius: 25px;
    cursor: pointer;
    margin-bottom: 25px;
    font-weight: 500;
    font-size: 14px;
    transition: all 0.3s ease;
    text-decoration: none;
}

.boton-volver:hover {
	display: block;
    background-color: rgba(255, 255, 255, 0.3);
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    text-decoration: none;
    color: white;
}

/* Contenedor principal */
.contenedor-principal {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    gap: 20px;
    width: 100%;
    padding: 25px;
    background-color: #f1eeee;
    border-radius: 30px;
    box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.2);
    margin-bottom: 30px;
    transition: all 0.3s ease;
}

/* Estilo específico para organizador */
body.organizador .contenedor-principal {
    border-top: 5px solid #9face6;
}

/* Usuario actual - borde dorado */
.es-usuario-actual .contenedor-principal {
    box-shadow: 0px 0px 0px 4px #ffcc00, 0px 8px 20px rgba(0, 0, 0, 0.2);
}

.imagen-usuario {
    width: 120px;
    height: 120px;
    min-width: 120px;
    max-width: 120px;
    border-radius: 20px;
    background-color: #e0e0e0;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #555;
    font-style: italic;
    font-size: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    flex-shrink: 0;
    overflow: hidden;
}

.imagen-usuario img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 20px;
}

.informacion-usuario {
    flex: 1;
    min-width: 0;
    position: relative;
    /* Necesario para que el botón se posicione dentro */
}

.nombre-usuario {
    margin: 0 0 10px 0;
    font-size: 32px;
    color: #4397db;
    font-weight: 700;
}

.nickname-usuario {
    margin: 0 0 20px 0;
    font-size: 18px;
    color: #666;
    font-weight: 500;
}

.detalles-usuario {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 12px;
    margin-top: 15px;
}

.detalle-item {
    display: flex;
    align-items: center;
    gap: 12px;
    font-size: 15px;
    color: #555;
    padding: 8px;
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 10px;
    transition: all 0.2s ease;
}

.detalle-item:hover {
    background-color: rgba(255, 255, 255, 0.7);
}

.icono {
    font-size: 16px;
    width: 20px;
    text-align: center;
}

/* Títulos de secciones */
.seccion-titulo {
    font-size: 24px;
    margin: 0 0 20px 0;
    color: white;
    padding-bottom: 10px;
    border-bottom: 2px solid rgba(255, 255, 255, 0.3);
    font-weight: 600;
}

/* Contenedor secundario */
.contenedor-secundario {
    background-color: #f1eeee;
    border-radius: 20px;
    padding: 25px;
    margin-bottom: 25px;
    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
}

.lista-items {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.item {
    padding: 20px;
    background-color: white;
    border-radius: 15px;
    box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
}

/* Diferente borde izquierdo según el tipo de usuario */
body.organizador .item {
    border-left: 4px solid #9face6;
}

body.asistente .item {
    border-left: 4px solid #74ebd5;
}

.item:hover {
    transform: translateY(-3px);
    box-shadow: 0px 6px 20px rgba(0, 0, 0, 0.15);
}

/* Estilos específicos para eventos (organizador) */
.info-evento {
    display: flex;
    flex-direction: column;
    gap: 8px;
    position: relative;
    /* Para que los botones se ubiquen dentro */
}

.nombre-evento {
    font-size: 18px;
    font-weight: 600;
    color: #4397db;
}

.nombre-evento-link{
	text-decoration: none;
	color: #4397db;
}

.detalle-evento {
    font-size: 14px;
    color: #666;
    font-weight: 500;
}

.fecha-evento,
.fecha-costo {
    font-size: 13px;
    color: #888;
    background-color: #f8f9fa;
    padding: 6px 10px;
    border-radius: 8px;
    display: inline-block;
    margin-top: 5px;
}

/* Estilos específicos para registros (asistente) */
.info-registro {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.detalle-registro {
    font-size: 14px;
    color: #666;
    font-weight: 500;
}

/* Información adicional (organizador) */
.info-adicional {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.item-info {
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 15px;
    background-color: white;
    border-radius: 12px;
    box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.08);
    transition: all 0.3s ease;
}

.item-info:hover {
    transform: translateY(-2px);
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.12);
}

.label {
    font-weight: 600;
    color: #4397db;
    min-width: 120px;
}

.item-info span:last-child,
.item-info a {
    color: #666;
    text-decoration: none;
}

.item-info a:hover {
    color: #4397db;
    text-decoration: underline;
}

/* Botones de acción */
.botones-accion {
    display: flex;
    gap: 15px;
    margin-top: 25px;
    justify-content: flex-end;
}

.boton {
    padding: 12px 24px;
    border-radius: 25px;
    border: none;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
    transition: all 0.3s ease;
    min-width: 120px;
}

.boton-primario {
    background-color: #4397db;
    color: white;
    box-shadow: 0 4px 12px rgba(67, 151, 219, 0.3);
}

.boton-primario:hover {
    background-color: #3a7bc8;
    transform: translateY(-2px);
    box-shadow: 0 6px 16px rgba(67, 151, 219, 0.4);
}

.boton-secundario {
    background-color: #e0e0e0;
    color: #555;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.boton-secundario:hover {
    background-color: #d5d5d5;
    transform: translateY(-2px);
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
}

/* Botón editar perfil */
.boton-editar-perfil {
    position: absolute;
    top: 0;
    right: 0;
    background-color: #4397db;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 20px;
    font-weight: 600;
    font-size: 13px;
    cursor: pointer;
    transition: all 0.3s ease;
    box-shadow: 0 3px 10px rgba(67, 151, 219, 0.3);
}

.boton-editar-perfil:hover {
    background-color: #3a7bc8;
    transform: translateY(-2px);
}

/* Botones de evento - arriba a la derecha (organizador) */
.botones-evento {
    position: absolute;
    top: 0;
    right: 0;
    display: flex;
    gap: 8px;
}

.boton-evento {
    padding: 8px 16px;
    border-radius: 20px;
    border: none;
    font-weight: 600;
    font-size: 13px;
    cursor: pointer;
    transition: all 0.3s ease;
    min-width: 140px;
    font-family: 'Outfit', sans-serif;
}

.boton-tipo-registro {
    background-color: #74ebd5;
    color: #2d5a4f;
    box-shadow: 0 3px 8px rgba(116, 235, 213, 0.3);
    display: block;
	width: fit-content;
    text-decoration: none;
}

.boton-tipo-registro:hover {
    background-color: #5de0c6;
    transform: translateY(-2px);
    box-shadow: 0 5px 12px rgba(116, 235, 213, 0.4);
    text-decoration: none;
}

.boton-patrocinio {
    background-color: #9face6;
    color: #4a5d8f;
    box-shadow: 0 3px 8px rgba(159, 172, 230, 0.3);
    display: block;
	width: fit-content;
    text-decoration: none;
}

.boton-patrocinio:hover {
    background-color: #8a9bdc;
    transform: translateY(-2px);
    box-shadow: 0 5px 12px rgba(159, 172, 230, 0.4);
    text-decoration: none;
}</style>
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
														<a href="${pageContext.request.contextPath}/altaPatrocinio" class="boton-evento boton-patrocinio">Nuevo Patrocinio</a>														
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