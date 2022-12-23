package ch.ffhs.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SseApplication is the root class which executes the application.
 * It gets converted to a Spring Boot application with the annotation @SpringBootApplication
 */

@SpringBootApplication
public class SseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseApplication.class, args);
    }

}
