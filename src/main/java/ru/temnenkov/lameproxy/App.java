package ru.temnenkov.lameproxy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;
import sockslib.common.methods.UsernamePasswordMethod;
import sockslib.server.SessionManager;
import sockslib.server.SocksProxyServer;
import sockslib.server.SocksServerBuilder;
import sockslib.server.manager.FileBasedUserManager;
import sockslib.server.manager.UserManager;

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

        UserManager userManager;
        try {
            userManager = new FileBasedUserManager(pars.getUsers(), true, 60000L);
        } catch (IOException e) {
            log.error("problem with users definition - wrong file path {}?", pars.getUsers(), e);
            return;
        }
        SocksProxyServer server;

        final SessionManager sessionManager = new CustomSessionManager();
        sessionManager.addSessionListener("trace", new TraceListener());

            server = SocksServerBuilder.newSocks5ServerBuilder()
                    .setBindPort(pars.getPort())
                    .setSessionManager(sessionManager)
                    .setUserManager(userManager).setSocksMethods
                            (new UsernamePasswordMethod()).build();


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
