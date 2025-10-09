<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="logica.datatypes.*"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">

    <%
        String tipo = (String) request.getAttribute("tipo");
        DataEdicion dataEd = (DataEdicion) request.getAttribute("dataEdicion");
        DataOrganizador dataOrg = (DataOrganizador) request.getAttribute("dataOrganizador");
        List<DataTRegistro> dataTRegistros = (List<DataTRegistro>) request.getAttribute("dataTRegistros");
        List<DataPatrocinioCompleto> dataPatrocinios = (List<DataPatrocinioCompleto>) request.getAttribute("dataPatrocinios");
    %>

    <title>Mis Eventos :: Consulta de Edición</title>

    <!-- Fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/consultaEdicionStyle.css">
</head>

<body>
    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

    <!-- SIDEBAR -->
    <jsp:include page="/WEB-INF/template/sidebar.jsp" />

    <!-- Contenido -->
    <div class="content">
        <div class="columna-izquierda"></div>

        <%
            if (dataOrg != null && dataEd != null) {
        %>

        <!-- Columna central -->
        <div class="columna-central">
            <div class="contenedor-principal">
                <img class="imagenes"
                     src="<%= request.getContextPath() %>/resources/images/IMG-NO.png"
                     alt="logoEdición" width="150px" height="150px">
                <div class="informacion-evento">
                    <h2 class="nombre-evento" id="nombreMaraton"><%= dataEd.getNombre() %></h2>
                    <div class="detalles-evento">
                        <span class="pais"><%= dataEd.getPais() %></span>
                        <span class="fechas">Desde: <%= dataEd.getFechaIni() %> - Hasta: <%= dataEd.getFechaFin() %></span>
                        <span class="fecha-alta">Alta: <%= dataEd.getFechaAlta() %></span>
                        <span class="siglas">SIG: <%= dataEd.getSigla() %></span>
                    </div>
                </div>
            </div>

            <% if (dataTRegistros != null && !dataTRegistros.isEmpty()) { %>
                <h2 class="texto-ed">Tipos de Registro:</h2>
                <div class="contenedorTRegistros">
                    <% for (DataTRegistro dataTR : dataTRegistros) { %>
                        <div class="contenedor">
                            <div class="informacion-TRegistro">
                                <h2 class="nombre-evento"><%= dataTR.getNombre() %></h2>
                                <div class="detalles-TRegistro">
                                    <span class="descripcion-TRegistro"><%= dataTR.getDescr() %>.</span>
                                    <span class="costo-TRegistro">Costo: $<%= dataTR.getCosto() %>.</span>
                                    <span class="Cupos-TRegistro"><%= dataTR.getCupo() %> cupos restantes.</span>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } %>

            <h2 class="texto-ed">Patrocinan:</h2>
            <% if (dataPatrocinios != null && !dataPatrocinios.isEmpty()) { %>
                <div class="contenedorTRegistros">
                    <% for (DataPatrocinioCompleto dataP : dataPatrocinios) { %>
                        <div class="contenedor">
                            <img class="imagenes"
                                 src="<%= request.getContextPath() %>/resources/images/IMG-NO.png"
                                 alt="logoPatro" width="120px" height="120px">
                            <div class="informacion-edicion">
                                <h2 class="nombre-evento"><%= dataP.getInstitucion() %></h2>
                                <div class="detalles-edicion">
                                    <span class="fecha-patrocinio">Fecha: <%= dataP.getFecha() %></span>
                                    <span class="aporte-patrocinio">Aporte: $<%= dataP.getMonto() %>.</span>
                                    <span class="nivel-patrocinio">Nivel: <%= dataP.getNivel() %>.</span>
                                    <span class="tipo-patrocinio">Código: <%= dataP.getCod() %>.</span>
                                    <span class="Cupos-patrocinio">Cupos: <%= dataP.getCtdCupo() %>.</span>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } %>
        </div>

        <!-- Columna derecha -->
        <div class="columna-derecha">
            <h2 class="texto-og">Organiza:</h2>

            <a href="../consultaUsuario/consultaDetalleOrganizador.html" class="contenedor-link">
                <div class="contenedor-derecha">
                    <img class="imagenes"
                         src="<%= request.getContextPath() %>/resources/images/IMG-NO.png"
                         alt="logoOrg" width="120" height="120">
                    <div class="informacion-TRegistro">
                        <h2 class="nombre-og"><%= dataOrg.getNombre() %></h2>
                        <div class="detalles-TRegistro">
                            <span class="email-org"><%= dataOrg.getEmail() %></span>
                        </div>
                    </div>
                </div>
            </a>

            <% if ("asistente".equals(tipo)) { %>
                <h2 class="texto-registro" id="registro-titulo">Registro:</h2>

                <div class="contenedor-derecha-alt" id="registro-detalle" style="display: flex;">
                    <div class="informacion-TRegistro">
                        <div class="detalles-TRegistro">
                            <div class="registro-item"><%= request.getAttribute("nickmail") %></div>

                            <%
                                Boolean registrado = (Boolean) request.getAttribute("registrado");
                                if (registrado != null && registrado) {
                                    ParEdicionRegistro dataRegistro = (ParEdicionRegistro) request.getAttribute("dataRegistro");
                            %>
                                <div class="registro-item">
                                    Fecha de registro: <%= dataRegistro.getFechaRegistro() %>
                                </div>
                                <div class="registro-item">
                                    <% //codigo?// %>
                                </div> 
                                <%
        							} 
   							%>                         
                              
                        </div>
                    </div>
                </div>

            <% } else if ("organizador".equals(tipo) && Boolean.TRUE.equals(request.getAttribute("organizaEdicion"))) { 
                   List<String> dataRegistros = (List<String>) request.getAttribute("dataRegistros");
            %>
                <h2 class="texto-registro" id="listado-titulo">Listado de registros:</h2>
                <% if (dataRegistros != null && !dataRegistros.isEmpty()) { %>
                    <div class="contenedor-derecha-alt" id="listado-registros">
                        <div class="informacion-TRegistro">
                            <div class="detalles-TRegistro">
                                <% for (String dataR : dataRegistros) { %>
                                    <div class="registro-item"><%= dataR %></div>
                                <% } %>
                            </div>
                        </div>
                    </div>
                <% } %>
            <% } %>
        </div>

    </div>

    <%
        } 
    %>
   
    
</body>
</html>
