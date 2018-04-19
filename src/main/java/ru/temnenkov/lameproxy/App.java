package ru.temnenkov.lameproxy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;
import sockslib.common.KeyStoreInfo;
import sockslib.common.SSLConfiguration;
import sockslib.common.methods.UsernamePasswordMethod;
import sockslib.server.SessionManager;
import sockslib.server.SocksProxyServer;
import sockslib.server.SocksServerBuilder;
import sockslib.server.manager.MemoryBasedUserManager;

import java.io.IOException;

@Slf4j
public class App {
    public static void main(String[] args) {

        AppParams pars = new AppParams();
        try {
            new JCommander(pars, args);

            new App().process(pars);
        } catch (ParameterException e) {
            new JCommander(pars).usage();
        } catch (Exception e) {
            log.error("fail start", e);
        }


    }

    private void process(AppParams pars) {
        final MemoryBasedUserManager userManager = new MemoryBasedUserManager();

        String[] users = pars.getUser().split(":");
        String[] pwds = pars.getPwd().split(":");
        for(int i=0; i < users.length; ++i) {
            userManager.addUser(users[i], pwds[i]);
        }

        SocksProxyServer server;

        final SessionManager sessionManager = new CustomSessionManager();
        sessionManager.addSessionListener("trace", new TraceAndCleanListener());

        if (pars.isSsl()) {

            final KeyStoreInfo keyStoreInfo = new KeyStoreInfo(pars.getKeyStorePath(), pars.getKeyStorePwd());
            SSLConfiguration ssl = new SSLConfiguration(keyStoreInfo, keyStoreInfo);

            server = SocksServerBuilder.newSocks5ServerBuilder()
                    .setBindPort(pars.getPort())
                    .setSessionManager(sessionManager)
                    .useSSL(ssl)
                    .setUserManager(userManager).setSocksMethods
                            (new UsernamePasswordMethod()).build();

        } else {
            server = SocksServerBuilder.newSocks5ServerBuilder()
                    .setBindPort(pars.getPort())
                    .setSessionManager(sessionManager)
                    .setUserManager(userManager).setSocksMethods
                            (new UsernamePasswordMethod()).build();
        }


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdown();
            log.info("SOCKS5 sever shutdown");
        }));

        try {
            server.start();
        } catch (IOException e) {
            log.error("fail start proxy", e);
        }
    }

}
