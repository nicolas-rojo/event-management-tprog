@echo off
title Deteniendo MisEventos

echo ========================================
echo   Deteniendo Servidor
echo ========================================
echo.

echo [1/2] Deteniendo Tomcat...
cd apache-tomcat-11.0.11\bin
call catalina.bat stop
cd ..\..
timeout /t 5 /nobreak

echo [2/2] Deteniendo aplicacion Swing...
taskkill /FI "WINDOWTITLE eq MisEventos*" /F 2>nul

echo.
echo Verificando que todo se detuvo...
timeout /t 3 /nobreak

netstat -ano | findstr :8080 >nul
if %errorlevel% equ 0 (
    echo [ADVERTENCIA] El puerto 8080 aun esta en uso.
    echo Presiona cualquier tecla para matar procesos Java manualmente...
    pause
    taskkill /F /IM java.exe 2>nul
)

echo.
echo ========================================
echo   Servidor detenido correctamente
echo ========================================
pause