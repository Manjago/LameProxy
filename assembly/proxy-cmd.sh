#!/bin/sh
java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-1.0-SNAPSHOT.jar -port 8383 -user test -pwd test