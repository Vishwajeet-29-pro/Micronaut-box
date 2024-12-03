package com.micronaut.simple.di;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class GreetingControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void test_dependency_injection() {
        String response = client.toBlocking().retrieve("/greet/micronaut");
        assertEquals("Hello micronaut, Welcome.", response);
    }
}