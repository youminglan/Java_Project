#!/bin/bash
mvn clean
mvn package -DskipTests
nohup java -jar target/server-0.0.1.jar &
echo succeed, press enter to exit!