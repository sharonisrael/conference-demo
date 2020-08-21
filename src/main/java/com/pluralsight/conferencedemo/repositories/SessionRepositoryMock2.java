package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SessionRepositoryMock2 implements SessionRepository {

    private Map<Long, Session> sessionsMap;

    public SessionRepositoryMock2() {
        sessionsMap = mockSessionsMap();
    }

    private Map<Long, Session> mockSessionsMap() {
        sessionsMap = new HashMap<>();
        sessionsMap.put(1L, new Session(1L, "mock mock", "nice overview", 15));
        return sessionsMap;
    }

    @Override
    public Collection<Session> getAllSessions() {
        return sessionsMap.values();
    }

    @Override
    public Session getSession(Long id) {
        return sessionsMap.get(id);
    }

    @Override
    public Session createSession(Session session) {
        return sessionsMap.put(3L, session);
    }

    @Override
    public Session updateSession(Long id, Session session) {
        return sessionsMap.put(id, session);
    }

    @Override
    public Session findSessionId(Long id) {
        return sessionsMap.get(id);
    }
}
