#!/bin/sh
java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -user test -pwd test -keyStorePath example.jks -keyStorePwd changeme