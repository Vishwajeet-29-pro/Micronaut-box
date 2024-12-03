package com.micronaut.simple.di;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/greet")
public class GreetingController {

    @Inject
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Get("/{name}")
    public String greet(String name) {
        return greetingService.greeting(name);
    }
}
