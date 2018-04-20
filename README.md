# LameProxy

**LameProxy** is a pure-java command line applcation which can creating a **SOCKS5** server easily. It's powered by [sockslib](https://github.com/fengyouchao/sockslib)

# REQUIRE

**LameProxy** requres JRE 8+

# DOWNLOAD

You can download **LameProxy** at [Release Page](https://github.com/Manjago/LameProxy/releases)

# HOW TO USE
Edit and run command-file proxy-*.bat or proxy-*.sh,  Change parameters -port, puser, -pwd, -keyStorePath, -keyStorePwd

	java -server -Xmx256m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -users users.txt
	
or	

	nohup java -server -Xmx256m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-${project.version}.jar -port 8383 -users users.txt 1>out 2>err & echo $!>lame-proxy.pid;
	
Here are some options:

- -port=[NUMBER]
- -users=[PATH TO FILE WITH USERS AND PASSWORDS]