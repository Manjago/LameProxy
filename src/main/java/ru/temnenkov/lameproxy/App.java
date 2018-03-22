package ru.temnenkov.lameproxy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;
import sockslib.common.KeyStoreInfo;
import sockslib.common.SSLConfiguration;
import sockslib.common.methods.UsernamePasswordMethod;
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

        userManager.addUser(pars.getUser(), pars.getPwd());

        SocksProxyServer server;

        if (pars.isSsl()) {

            final KeyStoreInfo keyStoreInfo = new KeyStoreInfo(pars.getKeyStorePath(), pars.getKeyStorePwd());
            SSLConfiguration ssl = new SSLConfiguration(keyStoreInfo, keyStoreInfo);

            server = SocksServerBuilder.newSocks5ServerBuilder()
                    .setBindPort(pars.getPort())
                    .useSSL(ssl)
                    .setUserManager(userManager).setSocksMethods
                            (new UsernamePasswordMethod()).build();

        } else {
            server = SocksServerBuilder.newSocks5ServerBuilder()
                    .setBindPort(pars.getPort())
                    .setUserManager(userManager).setSocksMethods
                            (new UsernamePasswordMethod()).build();
        }


        try {
            server.start();
        } catch (IOException e) {
            log.error("fail start proxy", e);
        }
    }
}
