<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>
<%@ page import="com.miseventos.utils.fabricaWS" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.IOException" %>
<%@ page import="cliente.ws.eventos.ControladorEventoWSService" %>
<%@ page import="cliente.ws.eventos.IControladorEventoWS" %>
<%@ page import="cliente.ws.usuarios.ControladorUsuarioWSService" %>
<%@ page import="cliente.ws.usuarios.IControladorUsuarioWS" %>

<%@ page import="cliente.ws.eventos.DataEventoCompleto" %>
<%@ page import="cliente.ws.eventos.DataEdicion" %>

<!DOCTYPE html>
<html>

<%
	List<Object> resultados = (List<Object>) request.getAttribute("resultados");
	String query = (String) request.getAttribute("query");
	String ordenamiento = (String) request.getAttribute("ordenamiento");
%>

<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Búsqueda :: Mis Eventos</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/styles/resBusquedaStyle.css">
</head>

<body>
	<!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

    <!-- SIDEBAR -->
    <jsp:include page="/WEB-INF/template/sidebar.jsp" />
    
    <!-- RESULTADOS -->
    <div class="content">
    	<div class="search-header">
	        <div class="search-info">
	            <h2><%= resultados.size() %> resultados para "<%= query %>"</h2>
	        </div>
	    </div>
    	<div class="contenedores">
    		<%
    		if (resultados != null && resultados.size() > 0) {
    			for (Object res : resultados) {
    				if (res instanceof DataEventoCompleto) {
    					DataEventoCompleto evento = (DataEventoCompleto) res;
    					String nomNormal = nombreUtils.normalizarNombre(evento.getNombre());
    		%>
    					<a href="<%=request.getContextPath()%>/consultaEvento?evento=<%=java.net.URLEncoder.encode(evento.getNombre(), "UTF-8")%>" class="contenedor-link">
			                <div class="contenedor">
			                	<span class="badge badge-evento">Evento</span>
			                    <img class="imagenes"
			                         src="<%=request.getContextPath()%>/imagenes?id=EV-<%= nomNormal %>"
			                         alt="logoEvento" width="120px" height="120px"
			                         onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
			                    
			                    <div class="informacion">
			                        <h2><%=evento.getNombre()%></h2>
			                        <p><%=evento.getDescripcion()%></p>
			                        
			                        <% 
			                        if ("organizador".equals(session.getAttribute("tipoUsr"))) { 
			                        %>
				                        <form id="form-<%=evento.getNombre().replaceAll("\\s+", "_")%>" action="<%=request.getContextPath()%>/home" method="post" style="display: inline;">
				                            <input type="hidden" name="eventoDarBaja" value="<%=evento.getNombre()%>" />
				                            <div class="boton-div">
				                                <button type="button" class="btn-nuevaedicion" onclick="abrirModalBaja('<%=evento.getNombre().replace("'", "\\'")%>', event)"> Dar de baja </button>
				                            </div>
				                        </form>
			                        <% 
			                        } 
			                        %>
			                    </div>
			                </div>
			            </a>
    				<%
    				} else if (res instanceof DataEdicion) {
    					IControladorEventoWS IEV_WS = null;
    					String ev = fabricaWS.getURLControladorEvento();
    			    	try{
    			    		ControladorEventoWSService servicio = new ControladorEventoWSService(new URL(ev));
    			    		IEV_WS = servicio.getControladorEventoWSPort();
    			    	}
    			    	catch (Exception e) {
    			    		e.printStackTrace();
    			    	}
						
    					DataEdicion edicion = (DataEdicion) res;
    					String nomNormal = nombreUtils.normalizarNombre(edicion.getNombre());
    					
    					String eventoEncoded = java.net.URLEncoder.encode(IEV_WS.eventoTieneEdicion(edicion.getNombre()), "UTF-8");
                        String edicionEncoded = java.net.URLEncoder.encode(edicion.getNombre(), "UTF-8");
    				%>
    					<a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= eventoEncoded %>&edicion=<%= edicionEncoded %>" class="contenedor-link">
		                    <div class="contenedor">
		                    	<span class="badge badge-evento">Edición</span>
		                        <img class="imagenes" 
										src="<%=request.getContextPath()%>/imagenes?id=ED-<%= nomNormal %>"
										alt="logoEvento" 
										width="120px" height="120px"
										onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/IMG-NO.png';">
		                        <div class="informacion-edicion">
		                            <h2 class="nombre-evento"><%= edicion.getNombre() %></h2>
		                            <div class="detalles-edicion">
		                                <span class="siglas">SIGLA: <%= edicion.getSigla() %></span>
		                                <span class="pais"><%= edicion.getCiudad() %>, <%= edicion.getPais() %></span>
		                                <span class="fechas">Fecha Alta: <%= edicion.getFechaAlta() %></span>
		                                <span class="fechas">Desde: <%= edicion.getFechaIni() %> - Hasta: <%= edicion.getFechaFin() %></span>
		                            </div>
		                        </div>
		                    </div>
		                </a>
    				<%
    				}
    			}
    		} else {
    		%>
    			<div class="no-results">
			        <h2>No se encontraron resultados</h2>
			        <%
			            String queryDisplay = (String) request.getAttribute("query");
			            if (queryDisplay != null && !queryDisplay.trim().isEmpty()) {
			        %>
			            <p>No hay eventos o ediciones que coincidan con "<%= queryDisplay %>"</p>
			        <%
			            } else {
			        %>
			            <p>No hay eventos o ediciones disponibles</p>
			        <%
			            }
			        %>
			        <p>Intenta con otras palabras clave</p>
			    </div>
    		<%
    		}
    		%>
    	</div>
    </div>
    
    <div id="modalBaja" class="modal-overlay" onclick="cerrarModalBaja()">
        <div class="modal-content" onclick="event.stopPropagation()">
            <div class="modal-header">
                <h3>Confirmar baja</h3>
                <button class="modal-close" onclick="cerrarModalBaja()">✕</button>
            </div>
            <div class="modal-body">
                <p>¿Seguro que deseas dar de baja el evento <strong id="nombreEventoBaja"></strong>?</p>
            </div>
            <div class="modal-footer">
                <button class="modal-button cancel" onclick="cerrarModalBaja()">Cancelar</button>
                <button class="modal-button confirm" onclick="confirmarBaja()">Confirmar</button>
            </div>
        </div>
    </div>

	<script src="${pageContext.request.contextPath}/resources/scripts/indexScript.js"></script>
</body>
</html>