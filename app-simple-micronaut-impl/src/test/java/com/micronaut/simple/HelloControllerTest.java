package com.micronaut.simple;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HelloControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void testHelloController() {
        String response = httpClient.toBlocking().retrieve("/hello");

        assertEquals("Hello Micronaut", response);
    }

    @Test
    void testQueryValue() {
        String response = httpClient.toBlocking().retrieve("/hello/query?value=World");
        assertEquals("Hello World", response);
    }
}