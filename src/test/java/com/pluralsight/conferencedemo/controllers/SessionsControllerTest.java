package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Basically it's a mistake to put logic in controller test. I should have created a service
 * You can also do Web layer Unit testing using MockMvc
 * Unit test - It's not component test
 */
public class SessionsControllerTest {

    @Test
    public void lookupExistingSession() {
        // Given
        SessionRepository sessionRepository = mock(SessionRepository.class);
        when(sessionRepository.findSessionId(anyLong())).thenReturn(new Session(100L, "mock session", "learn mock", 150));

        // When
        SessionsController sessionsController = new SessionsController(sessionRepository);
        Session session = sessionsController.findSessionId(100L);

        //Then
        // assert all will check abd print all assertion failures
        assertAll(
                () -> assertThat(session.getSession_length()).isEqualTo(150));
//        assert (session.getSession_length() == 150);
    }

    @Test
    public void missingLookupExistingSession() {
        SessionRepository sessionRepository = mock(SessionRepository.class);
        when(sessionRepository.findSessionId(anyLong())).thenReturn(null);

        SessionsController sessionsController = new SessionsController(sessionRepository);
        Session session = null;
        try {
            session = sessionsController.findSessionId(50L);
            fail("Error should be here");
        } catch (Exception e) {
            assertEquals("Room 50 is missing", e.getMessage());
        }
    }
}
