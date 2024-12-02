package com.micronaut.simple;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/config")
public class ConfigController {

    @Value("${greetings.message}")
    String message;

    @Get
    public String configMessage() {
        return message;
    }
}
