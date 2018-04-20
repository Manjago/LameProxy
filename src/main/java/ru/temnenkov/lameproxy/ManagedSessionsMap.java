package ru.temnenkov.lameproxy;

import lombok.extern.slf4j.Slf4j;
import sockslib.server.Session;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
class ManagedSessionsMap extends ConcurrentHashMap<Long, Session> {
    @Override
    public Session remove(Object key) {
        final Session result = super.remove(key);
        log.debug("managed session size {}", this.size());
        return result;
    }
}
