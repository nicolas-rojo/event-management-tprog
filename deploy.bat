@echo off
set "MAVEN_CMD=%~dp0apache-maven-3.9.11\bin\mvn.cmd"
cd "%~dp0tarea1"
call "%MAVEN_CMD%" clean package
cd "%~dp0tarea2"
call "%MAVEN_CMD%" clean package
cd "%~dp0tarea3"
call "%MAVEN_CMD%" clean package
cd "%~dp0"