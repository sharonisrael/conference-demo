package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.AppConfig;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepositoryMock;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Basically it's a mistake to put logic in controller test. I should have created a service
 *
 * Run it with localhost:8080/api/v1/sessions
 */

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private SessionRepository sessionRepository;

    public SessionsController() {
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // Or can register several configuration classes and then need to refresh
//        ctx.register(AppConfig.class);
//        ctx.refresh()

        //this.sessionRepository = ctx.getBean("sessionRepository", SessionRepository.class);

        // original version without Beans dependency injection
        //private SessionRepository sessionRepository = new SessionRepositoryMock();
    }

    /**
     * Autowired - It's really amazing it knows to take from the registries in AppConfig the bean and run it
     * @param sessionRepository session repository from bean
     */
    @Autowired
    public SessionsController(SessionRepository sessionRepository) {
        // Basically it's a mistake to put logic in controller test. I should have created a service
        this.sessionRepository = sessionRepository;
    }

    /*
    This can be also possible
    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    */


    /**
     * In browser use localhost:8080/api/v1/sessions
     * In postman use Get localhost:8080/api/v1/sessions
     *
     * @return list of sessions
     */
    @GetMapping
    public Collection<Session> list() {
        // Basically it's a mistake to put logic in controller test. I should have created a service
        // a service would know to sort and filter
        return sessionRepository.getAllSessions();
    }

    /**
     * In browser use localhost:8080/api/v1/sessions/1
     * In postman use Get localhost:8080/api/v1/sessions/2
     *
     * @param id session id
     * @return session
     */
    @GetMapping
    @RequestMapping("{id}")
    public Session getSession(@PathVariable Long id) {
        // Basically it's a mistake to put logic in controller test. I should have created a service
        // a service would know to sort and filter
        return sessionRepository.getSession(id);
    }

    /**
     * In postman use Post localhost:8080/api/v1/sessions/create
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
    @ResponseStatus(HttpStatus.CREATED)
    public Session createSession(@RequestBody Session session) {
        return sessionRepository.createSession(session);
    }

    /**
     * In postman use Post localhost:8080/api/v1/sessions/1
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

    public Session findSessionId(long l) throws RuntimeException {
        // return null;
//        return sessionRepository.findSessionId(l);

        // Basically it's a mistake to put logic in controller test. I should have created a service
        // a service would know to sort and filter
        Session session =  sessionRepository.findSessionId(l);
        if (session == null) {
            throw new RuntimeException("Room " + l + " is missing");
        }
        return session;
    }
}
