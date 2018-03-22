# LameProxy

**LameProxy** is a command line applcation which can  creating a **SOCKS5** server easily. It's powered by [sockslib](https://github.com/fengyouchao/sockslib)

# REQUIRE

**LameProxy** requres JRE 8+

# HOW TO USE
Edit and run command-file proxy-cmd.bat, nohup-proxy-cmd.sh or proxy-cmd.sh.  Change parameters -port, puser, -pwd

	java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-1.0.jar -port 8181 -user test -pwd test
	
Here are some options:

- -port=[NUMBER]
- -user=[USERNAME]
- -pwd=[PASSWORD]
