package com.micronaut.postgres;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class LibraryControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Inject
    private LibraryService libraryService;

    @MockBean(LibraryService.class)
    LibraryService mockLibraryService() {
        return mock(LibraryService.class);
    }

    @Test
    void test_post_library_should_return_created() {
        LibraryRequest libraryRequest = new LibraryRequest("Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);
        LibraryResponse libraryResponse = new LibraryResponse(UUID.randomUUID(), "Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);

        when(libraryService.addLibraryDetails(libraryRequest)).thenReturn(libraryResponse);

        HttpResponse<LibraryResponse> libraryResponseHttpResponse = httpClient.toBlocking().exchange(
                HttpRequest.POST("/api/v1/library", libraryRequest),
                LibraryResponse.class
        );

        assertEquals(201, libraryResponseHttpResponse.getStatus().getCode());
        assertNotNull(libraryResponseHttpResponse);
        assertEquals("Central Library", libraryResponseHttpResponse.body().getName());
        assertTrue(libraryResponseHttpResponse.body().isActive());
    }
}