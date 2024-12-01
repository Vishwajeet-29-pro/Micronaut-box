package com.micronaut.simple;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/hello")
public class HelloController {

    @Get
    public String index() {
        return "Hello Micronaut";
    }

    @Get("/query")
    public String queryMethod(@QueryValue(defaultValue = "Micronaut") String value) {
        return "Hello "+value;
    }
}
