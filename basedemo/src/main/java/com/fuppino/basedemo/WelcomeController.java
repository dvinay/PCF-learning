package com.fuppino.basedemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${WELCOME_MESSAGE:NOT_SET}")
    String greeting;

    public WelcomeController() {
        this.greeting = "hello";
    }

    public WelcomeController(String greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/")
    public String sayHello() {
        return greeting;
    }
}
