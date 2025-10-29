<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home :: Mis Eventos</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        
    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/listarEventosStyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/templateStyle.css">
</head>

<body>
    <!-- TOPBAR -->
    <jsp:include page="/WEB-INF/template/topbar.jsp" />

	<!-- CARDS -->

    <!-- LOWBAR -->
    <jsp:include page="/WEB-INF/template/lowbar.jsp" />
</body>
</html>