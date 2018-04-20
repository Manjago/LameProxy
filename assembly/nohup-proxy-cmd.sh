#!/bin/sh
nohup java -server -Xmx256m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -users users.txt 1>out 2>err & echo $!>lame-proxy.pid;