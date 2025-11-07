@echo off
chcp 65001 >nul
cd "%~dp0tarea1\target"
start "Tarea1-LogicaCentral" javaw -jar tarea1-1.0-SNAPSHOT.jar
cd "%~dp0"
timeout /t 10 /nobreak
set "CATALINA_HOME=C:\Program Files\Apache Software Foundation\Tomcat 11.0"
call "%CATALINA_HOME%\bin\startup.bat"
pause