@echo off
chcp 65001 >nul
echo [1/2] Deteniendo Tomcat...
set "CATALINA_HOME=C:\Program Files\Apache Software Foundation\Tomcat 11.0"
call "%CATALINA_HOME%\bin\shutdown.bat"
timeout /t 5 /nobreak
echo [2/2] Deteniendo servicio de logica central...
taskkill /FI "WINDOWTITLE eq Tarea1-LogicaCentral*" /F
pause