<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miseventos.utils.nombreUtils"%>
<%@ page import= "cliente.ws.usuarios.*"%>
<%@ page import= "cliente.ws.eventos.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miseventos.utils.fabricaWS" %>

<!DOCTYPE html>
<html>

<%
DataAsistente usr = (DataAsistente) request.getAttribute("asistente");
List<ParEdicionRegistro> registros = (List<ParEdicionRegistro>) request.getAttribute("registros");
%>

<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title> <%= usr.getNickname() %> :: Mis Eventos</title>
	
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/detalleUsuarioStyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">
</head>

<body>
	 <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />
    
    <!-- CARDS -->
    <div class="info-container">
    	<!-- Card Usuario -->
		<div class="profile-card">
  			<div class="profile-left">
    			<div class="profile-avatar">
      				<img src="<%=request.getContextPath()%>/imagenes?id=USR-<%= nombreUtils.normalizarNombre(usr.getNickname()) %>"
						alt="logoUsr" 
						onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/resources/images/stockusr.png';">
    			</div>
    			<p class="profile-nickname">@<%= usr.getNickname() %></p>
  			</div>
  
  			<div class="profile-right">
    			<div class="profile-info">
      				<h2 class="profile-name"><%= usr.getNombre() %> <%= usr.getApellido() %></h2>
    			</div>
    			<a class="logout-button" href="${pageContext.request.contextPath}/logout">
      				<i class="bi bi-box-arrow-right"></i>
      				Cerrar Sesión
    			</a>
  			</div>
		</div>
		
		<!-- Cards Registros -->
    	<div class="registros-section">
    		<% 
    		if (registros != null && !registros.isEmpty()) {
    		%>
    			<h2 class="section-title">Mis Registros a Eventos</h2>
    			<div class="registros-container">
	    			<%
	    			IControladorEventoWS IEV_WS = fabricaWS.getControladorEventoWS();
	    			IControladorUsuarioWS ICU_WS = fabricaWS.getControladorUsuarioWS();
	    			for (ParEdicionRegistro par : registros) {
                    	DataDetalleRegistro DataReg = ICU_WS.getDetallesRegistro(usr.getNickname(), par);
	    			%>
		        		<div class="registro-card">
		            		<a href="<%= request.getContextPath() %>/consultaEdicion?evento=<%= java.net.URLEncoder.encode(IEV_WS.eventoTieneEdicion(par.getNombreEdicion()), "UTF-8") %>&edicion=<%= java.net.URLEncoder.encode(par.getNombreEdicion(),"UTF-8") %>" class="registro-link">
		                		<div class="registro-info">
									<h3 class="registro-evento-nombre"><%= par.getNombreEdicion() %></h3>
									<p class="registro-detalle">Tipo: <%= DataReg.getTipoRegistro() %> </p>
									<span class="registro-fecha-costo">Fecha: <%= DataReg.getFechaRegistro() %> - Costo: $<%= DataReg.getCosto() %></span>
		                		</div>
		            		</a>
		            		
		            		<%
		            		if (!ICU_WS.verificarAsistencia(DataReg.getNombreEdicion(), usr.getNickname())) {
		            		%>
								<button type="button" class="asistencia-button" onclick="mostrarModalAsistencia(event, '<%= par.getNombreEdicion() %>')">
									<i class="bi bi-plus-circle"></i>
								</button>
		            		<%
		            		}		            		
		            		%>
		        		</div>
		        	<% 
	    			}
	    			%>
	    		</div>
	    	<%
    		} else {
    		%>
    			<div class="no-regs-message">
					<i class="bi bi-calendar-x"></i>
					<p>Aún no te has registrado a ninguna edición.</p>
				</div>
    		<%	
    		}
		    %>
		</div>
    </div>
    
    <!-- POPUP Confirmacion -->
	<div id="modalAsistencia" class="modal-overlay">
	    <div class="modal-content" onclick="event.stopPropagation()">
	        <form id="formConfirmarAsistencia" action="<%= request.getContextPath() %>/detalleUsuario" method="post">
	            <div class="modal-header">
	                <h3>Confirmar Asistencia</h3>
	                <button type="button" class="modal-close" onclick="cerrarModal()">
	                    <i class="bi bi-x-lg"></i>
	                </button>
	            </div>
	            <div class="modal-body">
	                <p>¿Confirmas tu asistencia a <strong id="nombreEvento"></strong>?</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="modal-button cancel" onclick="cerrarModal()">Cancelar</button>
	                <button type="submit" class="modal-button confirm">Confirmar</button>
	            </div>
	            
	            <input type="hidden" id="nombreEdicionInput" name="nombreEdicion" value="">
	            <input type="hidden" name="nickname" value="<%= usr.getNickname() %>">
	        </form>
	    </div>
	</div>
    
    <!-- LOWBAR -->
    <jsp:include page="/WEB-INF/template/lowbar.jsp" />
    
    <!-- SCRIPT -->
	<script src="${pageContext.request.contextPath}/resources/scripts/detalleUsuarioScript.js"></script></body>
</html>