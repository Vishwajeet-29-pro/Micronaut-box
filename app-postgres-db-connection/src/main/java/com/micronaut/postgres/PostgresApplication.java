package com.micronaut.postgres;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Micronaut-postgres-database-connection",
                version = "1.0"
        ), servers = @Server(url = "http://localhost:8080/")
)
public class PostgresApplication {
    public static void main(String[] args) {
        Micronaut.run(PostgresApplication.class);
    }
}