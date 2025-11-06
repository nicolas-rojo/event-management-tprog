@echo off
title MisEventos - Servidor

echo ========================================
echo   Iniciando MisEventos
echo ========================================
echo.

REM Paso 1: Iniciar aplicación Swing
echo [1/3] Iniciando aplicacion Swing...
start "MisEventos" javaw -jar tarea1-1.0-SNAPSHOT-jar-with-dependencies.jar

echo.
echo ========================================
echo   ATENCION
echo ========================================
echo.
echo 1. En la ventana que se abrio, ve al menu:
echo    Sistema ^> Publicar WebServices
echo.
echo 2. Espera el mensaje de confirmacion
echo.
echo 3. Luego presiona cualquier tecla aqui para continuar...
echo.
pause

REM Paso 2: Iniciar Tomcat en segundo plano
echo.
echo [2/3] Iniciando Tomcat en segundo plano...
cd apache-tomcat-11.0.11\bin
start "" /min javaw -Dcatalina.home=.. -Dcatalina.base=.. -cp "..\bin\bootstrap.jar;..\bin\tomcat-juli.jar" org.apache.catalina.startup.Bootstrap start
cd ..\..

echo.
echo [3/3] Esperando que Tomcat inicie (30 segundos)...
timeout /t 30 /nobreak

REM Abrir navegadores
echo.
echo Abriendo aplicaciones web...
start http://localhost:8080/tarea2
timeout /t 2 /nobreak >nul
start http://localhost:8080/tarea3

echo.
echo ========================================
echo   SERVIDOR INICIADO
echo ========================================
echo.
echo Aplicaciones disponibles:
echo   - Web Desktop: http://localhost:8080/tarea2
echo   - Web Movil:   http://localhost:8080/tarea3
echo.
echo Web Services publicados en:
echo   - http://localhost:8081/eventos?wsdl
echo   - http://localhost:8081/usuarios?wsdl
echo   - http://localhost:8081/instituciones?wsdl
echo.
echo Ahora puedes cerrar esta ventana.
echo El servidor seguira corriendo en segundo plano.
echo.
echo Para detener, ejecuta: detener.bat
echo.
pause