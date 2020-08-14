package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepositoryMock;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Run it with localhost:8080//api/v1/sessions
 */

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private SessionRepository sessionRepository = new SessionRepositoryMock();

    /**
     * In browser use localhost:8080//api/v1/sessions
     * In postman use Get localhost:8080//api/v1/sessions
     *
     * @return list of sessions
     */
    @GetMapping
    public Collection<Session> list() {
        return sessionRepository.getAllSessions();
    }

    /**
     * In browser use localhost:8080//api/v1/sessions/1
     * In postman use Get localhost:8080//api/v1/sessions/2
     *
     * @param id session id
     * @return session
     */
    @GetMapping
    @RequestMapping("{id}")
    public Session getSession(@PathVariable Long id) {
        return sessionRepository.getSession(id);
    }

    /**
     * In postman use Post localhost:8080//api/v1/sessions/create
     * Mark in body the Raw
     * VERY IMPORTANT change text type to JSON
     * {
     *     "session_id": 1,
     *     "session_name": "git hub",
     *     "session_description": "nice overview",
     *     "session_length": 15
     * }
     *
     * @param session new session
     * @return the new session
     */
    @PostMapping
    @RequestMapping("create")
    public Session createSession(@RequestBody Session session) {
        return sessionRepository.createSession(session);
    }

    /**
     * In postman use Post localhost:8080//api/v1/sessions/1
     * Mark in body the Raw
     * VERY IMPORTANT change text type to JSON
     * {
     *     "session_id": 1,
     *     "session_name": "git hubX",
     *     "session_description": "nice overview",
     *     "session_length": 15
     * }
     *
     * @param id session id
     * @param session session new data
     * @return the new session
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session updateSession(@PathVariable Long id, @RequestBody Session session) {
        return sessionRepository.updateSession(id, session);
    }
}
