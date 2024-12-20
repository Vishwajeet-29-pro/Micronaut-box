package com.micronaut.h2;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Micronaut-h2-database-connection",
                version = "1.0"
        ), servers = @Server(url = "http://localhost:8080/")
)
public class H2Application {
    public static void main(String[] args) {
        Micronaut.run(H2Application.class);
    }
}