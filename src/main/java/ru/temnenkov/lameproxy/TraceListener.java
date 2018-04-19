package ru.temnenkov.lameproxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockslib.common.AddressType;
import sockslib.server.Session;
import sockslib.server.listener.CloseSessionException;
import sockslib.server.listener.LoggingListener;
import sockslib.server.listener.SessionListener;
import sockslib.server.msg.CommandMessage;

@Slf4j
public class TraceListener implements SessionListener {


  @Override
  public void onCreate(Session session) throws CloseSessionException {
    log.info("Create SESSION[{}] for {}", session.getId(), session.getClientAddress());
  }

  @Override
  public void onCommand(Session session, CommandMessage message) throws CloseSessionException {
    log.info("SESSION[{}] request:{}  {}:{}", session.getId(), message.getCommand(),
        message.getAddressType() != AddressType.DOMAIN_NAME ?
            message.getInetAddress() :
            message.getHost(), message.getPort());
  }

  @Override
  public void onClose(Session session) {
    log.info("SESSION[{}] closed, stat {}", session.getId(), session.getNetworkMonitor());
    session.getManagedSessions().remove(session.getId());
    log.debug("managed sessions: {}", session.getManagedSessions().size());
  }

  @Override
  public void onException(Session session, Exception exception) {
    log.error("SESSION[{}] occurred error:{}, message:{}", session.getId(), exception.getClass
        ().getSimpleName(), exception.getMessage(), exception);
  }
}
