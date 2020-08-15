package com.pluralsight.conferencedemo;

import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepositoryMock;
import com.pluralsight.conferencedemo.repositories.SessionRepositoryMock2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "sessionRepository")
    public SessionRepository getSessionRepository() {
        //        return new SessionRepositoryMock();
        return new SessionRepositoryMock2();
    }

}
