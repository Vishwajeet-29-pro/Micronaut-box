package com.micronaut.postgres;

import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@MicronautTest
class LibraryControllerTest {

    @Inject
    @Client
    HttpClient httpClient;

    @Inject
    private LibraryService libraryService;

    @MockBean(LibraryService.class)
    LibraryService mockLibraryService() {
        return mock(LibraryService.class);
    }
}