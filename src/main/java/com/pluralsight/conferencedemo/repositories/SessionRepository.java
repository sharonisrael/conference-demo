package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import java.util.Collection;

public interface SessionRepository {

    Collection<Session> getAllSessions();

    Session getSession(Long id);

    Session createSession(Session session);

    Session updateSession(Long id, Session session);

    Session findSessionId(Long id);
}
