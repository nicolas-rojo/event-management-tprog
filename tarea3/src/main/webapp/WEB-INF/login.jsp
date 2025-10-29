<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Login :: Mis Eventos</title>
	
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    
    <!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/loginStyle.css">
</head>

<body>
	<img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logo">

    <div class="container d-flex flex-column align-items-center justify-content-center vh-100">
        <div class="card shadow-sm p-4 w-100" style="max-width: 400px;">
            <h2 class="text-center mb-4">Iniciar Sesión</h2>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" class="form-control mb-3" id="usuario" name="nickmail" placeholder="Nickname / Mail" required>
                <input type="password" class="form-control mb-3" id="clave" name="clave" placeholder="Contraseña" required>
                <div id="confirmarError" class="text-danger mb-2"></div>
                <button type="submit" class="btn btn-primary w-100">Entrar</button>
            </form>
        </div>
    </div>
</body>
</html>