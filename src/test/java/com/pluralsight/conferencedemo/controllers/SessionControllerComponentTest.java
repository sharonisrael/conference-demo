package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * This is on top of the controller unit test
 * Adding RunWith is adding JUnit to path
 *
 * You can also do Web layer Unit testing using MockMvc
 * Or Integration testing using Spring’s TestRestTemplate example
 *
 * The @SpringBootTest annotation tells Spring Boot to look for a main configuration class with @SpringBootApplication
 * In our case ConferenceDemoApplication is @SpringBootApplication
 *
 * Found explanation in https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing
 *
 * The @SpringBootTest annotation can be used when we need to bootstrap the entire container.
 * Spring Boot provides a @SpringBootTest annotation, which can be used as an alternative to the
 * standard spring-test @ContextConfiguration annotation when you need Spring Boot features
 *
 * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
 * Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
 * If you are using JUnit 4, don’t forget to also add @RunWith(SpringRunner.class) to your test,
 * otherwise the annotations will be ignored. If you are using JUnit 5, there’s no need to add the equivalent
 *
 *  By default, @SpringBootTest will not start a server.
 *  You can use the webEnvironment attribute of @SpringBootTest to further refine how your tests run:
 *  Pick up random port (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 *  Or (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) which is 8080
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SessionControllerComponentTest {

    /*
     * @SpringBootTest registers a TestRestTeplate bean so we can directly @Autowire
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void get_session_ReturnsSession_OK() {

        // This is not related to the Autowired SpringBootTest port
//        final private static int port = 8080;
//        final private static String baseUrl = "http://localhost:";
//        ResponseEntity<Session> directCallResponseEntity =
//                testRestTemplate.getForEntity(baseUrl + port + "/api/v1/sessions/1", Session.class);

        // getForEntitiy translates to POJO from Plain JSON
        // there is also getForObject which is similar (i didnt understand the difference)
        ResponseEntity<Session> responseEntity =
                testRestTemplate.getForEntity(testRestTemplate.getRootUri() + "/api/v1/sessions/1", Session.class);

        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().getSession_id()).isEqualTo(1)
        );
    }

    @Test
    public void create_session_ReturnsSession_OK() {
        // Post message requires BODY
        String resourceURL = testRestTemplate.getRootUri() + "/api/v1/sessions/create";
        HttpEntity<Session> request = new HttpEntity<>(
                new Session(80L, "post test", "use postForEntity", 200));
        ResponseEntity<Session> responseEntity =
                testRestTemplate.postForEntity(resourceURL, request, Session.class);

        // Alternative option using exchange
//        HttpMethod method = HttpMethod.PUT;
//        ResponseEntity<Session> responseEntity =
//                testRestTemplate.exchange(resourceURL, method, request, Session.class);

        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED), // Not HttpStatus.OK
                () -> assertThat(responseEntity.getBody().getSession_length()).isEqualTo(200)
        );
    }


    @Test
    public void update_session_ReturnsSession_OK() {
        // Post message requires BODY
        String resourceURL = testRestTemplate.getRootUri() + "/api/v1/sessions/1";
        HttpMethod method = HttpMethod.PUT;
        HttpEntity<Session> request = new HttpEntity<>(
                new Session(80L, "post test", "use postForEntity", 200));

        ResponseEntity<Session> responseEntity =
                testRestTemplate.exchange(resourceURL, method, request, Session.class);

        assertAll(
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseEntity.getBody().getSession_length()).isEqualTo(200)
        );
    }

}
