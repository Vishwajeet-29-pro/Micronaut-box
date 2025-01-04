package com.micronaut.postgres;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class OpenApiGeneratedTest {

    @Test
    void buildGeneratesOpenApi(ResourceLoader resourceLoader) {
        assertTrue(resourceLoader.getResource("META-INF/swagger/micronaut-postgres-database-connection-1.0.yml").isPresent());
        assertTrue(resourceLoader.getResource("META-INF/swagger/views/swagger-ui/index.html").isPresent());
    }

    @Test
    void openApi(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        assertDoesNotThrow(() -> client.exchange("/swagger/micronaut-postgres-database-connection-1.0.yml"));
        assertDoesNotThrow(() -> client.exchange("/swagger-ui/index.html"));
    }
}