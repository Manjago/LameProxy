# LameProxy

**LameProxy** is a pure-java command line applcation which can creating a **SOCKS5** server easily. It's powered by [sockslib](https://github.com/fengyouchao/sockslib)

# REQUIRE

**LameProxy** requres JRE 8+

# DOWNLOAD

You can download **LameProxy** at [Release Page](https://github.com/Manjago/LameProxy/releases)

# HOW TO USE
Edit and run command-file proxy-*.bat or proxy-*.sh,  Change parameters -port, puser, -pwd, -keyStorePath, -keyStorePwd

	java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-1.0.jar -port 8181 -user test -pwd test
	
or	

	java -Xmx100m -Dlogback.configurationFile=logback.xml -jar uber-lameproxy-1.0.jar -port 8181 -user test -pwd test -keyStorePath example.jks -keyStorePwd changeme
	
Here are some options:

- -port=[NUMBER]
- -user=[USERNAME]
- -pwd=[PASSWORD]
- -keyStorePath=[PATH TO JKS-STORE]
- -keyStorePwd=[PWD FOR JKS-STORE]
