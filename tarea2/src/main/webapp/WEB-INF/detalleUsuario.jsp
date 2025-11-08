<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.IOException" %>
<%@ page import="cliente.ws.usuarios.DataUsuario" %>
<%@ page import="cliente.ws.usuarios.DataAsistente" %>
<%@ page import="cliente.ws.usuarios.DataOrganizador" %>
<%@ page import="cliente.ws.usuarios.ParEdicionRegistro" %>
<%@ page import="cliente.ws.usuarios.DataDetalleRegistro" %> <!--  Revisar el import este -->
<%@ page import="cliente.ws.eventos.DataEdicionWeb" %> <!--  Revisar el import este -->
<%@ page import="cliente.ws.usuarios.Estado" %>



<%@ page import="cliente.ws.eventos.ControladorEventoWSService" %>
<%@ page import="cliente.ws.eventos.IControladorEventoWS" %>
<%@ page import="cliente.ws.usuarios.ControladorUsuarioWSService" %>
<%@ page import="cliente.ws.usuarios.IControladorUsuarioWS" %>

<%@ page import="com.miseventos.utils.nombreUtils" %>

<%@ page import="com.miseventos.utils.fabricaWS" %>

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
        List<String> seguidos = (List<String>) request.getAttribute("seguidos");
        List<String> seguidores = (List<String>) request.getAttribute("seguidores");
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
            	<div class="contenedor-imagen-stats">
	                <div class="imagen-usuario">
	                    <img src="<%=request.getContextPath()%>/imagenes?id=USR-<%= nomNormal %>"
	                         alt="<%= usuario.getNombre() %>" 
	                         onerror="this.style.display='none'; this.parentElement.innerHTML='Sin imagen'">
	                </div>
	                
	                <div class="estadisticas-usuario">
						<div class="estadistica-item" onclick="abrirModal('seguidores')">
					        <div class="estadistica-numero"><%= seguidores != null ? seguidores.size() : 0 %></div>
					        <div class="estadistica-label">Seguidores</div>
					    </div>
					    <div class="estadistica-item" onclick="abrirModal('seguidos')">
					        <div class="estadistica-numero"><%= seguidos != null ? seguidos.size() : 0 %></div>
					        <div class="estadistica-label">Seguidos</div>
					    </div>
					</div>            	
            	</div>
                
                <div class="informacion-usuario">
                    <h1 class="nombre-usuario"><%= usuario.getNombre() %></h1>
                    <p class="nickname-usuario">@<%= usuario.getNickname() %></p>
                    
                    
                    
                    <%if(usuario.getEmail().equals(loggedMail)){%>
					    <a href="${pageContext.request.contextPath}/ModificarUsuario" class="boton-editar-perfil">✏️ Editar Perfil</a>
					<%
					} else {
						if (usr != null) {
							IControladorUsuarioWS ICU_WS = null;
					    	String usr9 = fabricaWS.getURLControladorUsuario();
					    	try{
					        	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService(new URL(usr9));
					            ICU_WS = servicio2.getControladorUsuarioWSPort();
					    	}
					    	catch (Exception e) {
					    		e.printStackTrace();
					    	}
							boolean esSeguidor = ICU_WS.esSeguidor(usuario.getEmail(), loggedMail);
							if (esSeguidor) {
							%>
								<form action="${pageContext.request.contextPath}/detalleUsuario" method="POST" style="display: inline;">
						            <input type="hidden" name="accion" value="dejar_seguir">
						            <input type="hidden" name="emailASeguir" value="<%= usuario.getEmail() %>">
						            <button type="submit" class="boton-seguir siguiendo">- Dejar de seguir</button>
						        </form>
							<%
							} else { %>
								<form action="${pageContext.request.contextPath}/detalleUsuario" method="POST" style="display: inline;">
						            <input type="hidden" name="accion" value="seguir">
						            <input type="hidden" name="emailASeguir" value="<%= usuario.getEmail() %>">
						            <button type="submit" class="boton-seguir">+ Seguir</button>
					        	</form>
				        	<%
							}
						}
                	}
					%>
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
                                    IControladorUsuarioWS ICU_WS = null;
                                    IControladorEventoWS IEV_WS = null;
                                    
                                    String ev = fabricaWS.getURLControladorEvento();
                                	String usr5 = fabricaWS.getURLControladorUsuario();
                                	try{
                                		ControladorEventoWSService servicio = new ControladorEventoWSService(new URL(ev));
                                		IEV_WS = servicio.getControladorEventoWSPort();
                                    	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService(new URL(usr5));
                                        ICU_WS = servicio2.getControladorUsuarioWSPort();
                                	}
                                	catch (Exception e) {
                                		e.printStackTrace();
                                	}
                                        for (ParEdicionRegistro registro : registros) {
                                        	DataDetalleRegistro DataReg = ICU_WS.getDetallesRegistro(usuario.getNickname(), registro);
                                    %>
                                        <div class="item">
                                            <div class="info-registro">
                                            	<span class="nombre-evento">
                                                    ✅ <a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= java.net.URLEncoder.encode(IEV_WS.eventoTieneEdicion(registro.getNombreEdicion()), "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(registro.getNombreEdicion(),"UTF-8") %>" 
                                                    class = "nombre-evento-link"> 
                                                    <%= registro.getNombreEdicion() %> </a>
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
                            		if (usr != null && usr.getNickname().equals(usuario.getNickname())) {
                            %>
                            		<p style="text-align: center; color: #666; padding: 20px;">
                                        Aún no te has registrado a ninguna edición de evento.
                                    </p>
                                    <% 
                                    } else { 
                                    %>
                                    <p style="text-align: center; color: #666; padding: 20px;">
                                        Inicia sesión como <%= usuario.getNickname() %> para ver sus registros.
                                    </p>
                            <%
                            		}
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
                    // MOVER LA INICIALIZACIÓN AQUÍ, ANTES DEL IF
                    IControladorEventoWS IEV_WS = null;
                    String ev = fabricaWS.getURLControladorEvento();
                    try{
                        ControladorEventoWSService servicio = new ControladorEventoWSService(new URL(ev));
                        IEV_WS = servicio.getControladorEventoWSPort();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    if (ediciones != null && ediciones.length > 0 && IEV_WS != null) {
                %>
                        <div class="lista-items">
                        <%
                            for (DataEdicionWeb edicion : ediciones) {
                                String evento = IEV_WS.eventoTieneEdicion(edicion.getNombre());
                                if(edicion.getEstado() == cliente.ws.eventos.Estado.CONFIRMADO){
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
                                        String nombreEvento = IEV_WS.eventoTieneEdicion(edicion.getNombre());
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
                            <% if (IEV_WS == null) { %>
                                Error al conectar con el servicio de eventos.
                            <% } else { %>
                                No hay ediciones asociadas a este organizador.
                            <% } %>
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
    
    <!-- POPUP Seguidores -->
    <div id="modalSeguidores" class="modal-overlay" onclick="cerrarModal(event, 'modalSeguidores')">
	    <div class="modal-contenido" onclick="event.stopPropagation()">
	        <div class="modal-header">
	            <h3>Seguidores</h3>
	            <button class="modal-cerrar" onclick="cerrarModal(event, 'modalSeguidores')">&times;</button>
	        </div>
	        <div class="modal-body">
	            <% 
	            if (seguidores == null || seguidores.isEmpty()) { 
	            %>
	                <p class="modal-vacio">No hay seguidores aún</p>
	            <% } else {
	            	IControladorUsuarioWS ICU_WS = null;
	            	String usr2 = fabricaWS.getURLControladorUsuario();
	            	try{
	                	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService(new URL(usr2));
	                    ICU_WS = servicio2.getControladorUsuarioWSPort();
	            	}
	            	catch (Exception e) {
	            		e.printStackTrace();
	            	}
	            	
	            	String nickNorm;
	                for (String nickname : seguidores) {
	                	String tipoSeg = ICU_WS.getTipoUsuario(nickname);
	                	nickNorm = nombreUtils.normalizarNombre(nickname);
	                	DataUsuario seg = null;
	                	if ("Asistente".equals(tipoSeg)) {
		                	try {
		                		seg = ICU_WS.getAsistente(nickname);		                		
		                	} catch (Exception e) {}
	                	} else if ("Organizador".equals(tipoSeg)) {
		                	try {
			                	seg = ICU_WS.getOrganizador(nickname);	                		
		                	} catch (Exception e) {}
		                }	                		
	                	%>
	                    <div class="modal-usuario-item" onclick="window.location.href='${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(seg.getEmail(), "UTF-8") %>'">
	                        <div class="modal-usuario-avatar">
	                            <img src="<%=request.getContextPath()%>/imagenes?id=USR-<%= nickNorm %>"
			                         alt="<%= usuario.getNombre() %>" 
			                         onerror="this.style.display='none'; this.parentElement.innerHTML='Sin imagen'">
	                        </div>
	                        <div class="modal-usuario-nickname">@<%= nickname %></div>
	                    </div>
	            <%  }
	            } %>
	        </div>
	    </div>
	</div>
	
	<!-- POPUP Seguidos -->
	<div id="modalSeguidos" class="modal-overlay" onclick="cerrarModal(event, 'modalSeguidos')">
	    <div class="modal-contenido" onclick="event.stopPropagation()">
	        <div class="modal-header">
	            <h3>Seguidos</h3>
	            <button class="modal-cerrar" onclick="cerrarModal(event, 'modalSeguidos')">&times;</button>
	        </div>
	        <div class="modal-body">
	            <% 
	            if (seguidos == null || seguidos.isEmpty()) { 
	            %>
	                <p class="modal-vacio">No hay seguidos aún</p>
	            <% } else {
	            	IControladorUsuarioWS ICU_WS = null;
	            	String usr3 = fabricaWS.getURLControladorUsuario();
	            	try{
	                	ControladorUsuarioWSService servicio2 = new ControladorUsuarioWSService(new URL(usr3));
	                    ICU_WS = servicio2.getControladorUsuarioWSPort();
	            	}
	            	catch (Exception e) {
	            		e.printStackTrace();
	            	}
	            	
	            	String nickNorm;
	                for (String nickname : seguidos) {
	                	String tipoSeg = ICU_WS.getTipoUsuario(nickname);
	                	nickNorm = nombreUtils.normalizarNombre(nickname);
	                	DataUsuario seg = null;
	                	if ("Asistente".equals(tipoSeg)) {
		                	try {
		                		seg = ICU_WS.getAsistente(nickname);		                		
		                	} catch (Exception e) {}
	                	} else if ("Organizador".equals(tipoSeg)) {
		                	try {
			                	seg = ICU_WS.getOrganizador(nickname);	                		
		                	} catch (Exception e) {}
		                }	                		
	                	%>
	                    <div class="modal-usuario-item" onclick="window.location.href='${pageContext.request.contextPath}/detalleUsuario?email=<%= java.net.URLEncoder.encode(seg.getEmail(), "UTF-8") %>'">
	                        <div class="modal-usuario-avatar">
	                            <img src="<%=request.getContextPath()%>/imagenes?id=USR-<%= nickNorm %>"
			                         alt="<%= usuario.getNombre() %>" 
			                         onerror="this.style.display='none'; this.parentElement.innerHTML='Sin imagen'">
	                        </div>
	                        <div class="modal-usuario-nickname">@<%= nickname %></div>
	                    </div>
	            <%  }
	            } %>
	        </div>
	    </div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/scripts/detalleUsuario.js"></script>
</body>

</html>