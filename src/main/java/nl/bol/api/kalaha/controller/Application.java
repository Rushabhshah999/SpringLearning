package nl.bol.api.kalaha.controller;

import nl.bol.api.kalaha.docs.SpringFoxConfig;
import nl.bol.api.kalaha.exception.GlobalExceptionHandler;
import nl.bol.api.kalaha.service.GameService;
import nl.bol.api.kalaha.validator.PlayerNumberValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SpringFoxConfig.class,GameService.class, GlobalExceptionHandler.class, PlayerNumberValidator.class})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
