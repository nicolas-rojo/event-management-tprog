#!/bin/bash

java -jar tarea1-1.0-SNAPSHOT-jar-with-dependencies.jar &

cd apache-tomcat-11.0.11/bin
./catalina.sh start
cd ../..

exit