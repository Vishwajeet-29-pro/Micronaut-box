package com.micronaut.simple;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {

    @Get
    public String index() {
        return "Hello Micronaut";
    }
}
