#!/bin/sh
nohup java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -user test -pwd test 1>out 2>err & echo $!>lame-proxy.pid;