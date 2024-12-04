package com.micronaut.simple;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class LogControllerTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void testLog() {
        String response = httpClient.toBlocking().retrieve("/logger");
        assertEquals("Check log for details.", response);
    }
}