package ru.temnenkov.lameproxy;

import lombok.extern.slf4j.Slf4j;
import sockslib.server.Session;
import sockslib.server.listener.CloseSessionException;
import sockslib.server.listener.SessionListener;
import sockslib.server.msg.CommandMessage;

@Slf4j
class TraceListener implements SessionListener {
    @Override
    public void onCreate(Session session) throws CloseSessionException {
        log.debug("session {} create", session);
    }

    @Override
    public void onClose(Session session) {
        log.debug("session {} close", session);
    }

    @Override
    public void onException(Session session, Exception exception) {
        log.error("session {} exception", session, exception);

    }

    @Override
    public void onCommand(Session session, CommandMessage message) throws CloseSessionException {
        log.debug("session {} command {}", session, message);
    }
}
