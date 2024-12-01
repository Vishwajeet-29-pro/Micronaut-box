package com.micronaut.simple;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
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

    /**
     *    New Thing to learn no need of @PathVariable annotation
     *    simply pass the same variable as defined in path
     *    here `name` from @Get and `name` from method parameter is same
     *    You can also add @PathVariable annotation same as spring
    */
    @Get("/{name}")
    public String pathVariableMethod(String name) {
        return "Hello "+name;
    }
}
