#!/bin/sh
java -server -Xmx256m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -users users.txt