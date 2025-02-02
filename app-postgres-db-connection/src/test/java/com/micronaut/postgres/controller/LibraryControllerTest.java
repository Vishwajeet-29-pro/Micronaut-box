package com.micronaut.postgres.controller;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void test_get_all_libraries_should_return_library_response_list() {
        List<LibraryResponse> libraryResponses = List.of(
                new LibraryResponse(UUID.randomUUID(), "Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true),
                new LibraryResponse(UUID.randomUUID(), "JN Library", "ABC road, Pune", LocalDate.of(2005, 1,26), false)
        );

        when(libraryService.findAllLibraries()).thenReturn(libraryResponses);

        HttpResponse<List<LibraryResponse>> response = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/library"), Argument.listOf(LibraryResponse.class)
        );

        assertNotNull(response);
        assertEquals(2, response.body().size());
        assertEquals("Central Library", response.body().getFirst().getName());
        assertEquals("ABC road, Pune", response.body().get(1).getLocation());
        assertEquals(LocalDate.of(2004, 1,1 ), response.body().getFirst().getEstablishDate());
        assertTrue(response.body().getFirst().isActive());
        assertFalse(response.body().get(1).isActive());
    }

    @Test
    void test_get_by_id_should_return_library_response() {
        LibraryResponse libraryResponse = new LibraryResponse(UUID.randomUUID(), "Central Library", "SB road, Pune", LocalDate.of(2004,1,1 ), true);
        when(libraryService.getLibraryById(any(UUID.class))).thenReturn(libraryResponse);

        HttpResponse<LibraryResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/library/" + UUID.randomUUID()),
                LibraryResponse.class
        );
        assertEquals("SB road, Pune", response.body().getLocation());
        assertEquals("Central Library", response.body().getName());
        assertEquals(LocalDate.of(2004, 1, 1), response.body().getEstablishDate());
        assertTrue(response.body().isActive());
    }

    @Test
    void test_update_library_should_update_library_and_return_ok() {
        LibraryRequest libraryRequest = new LibraryRequest("New Central Library", "SB road, Pune", LocalDate.of(2005, 1, 1), true);
        UUID id = UUID.randomUUID();
        LibraryResponse libraryResponse = new LibraryResponse(id, "New Central Library", "SB road, Pune", LocalDate.of(2005, 1, 1), true);

        when(libraryService.updateLibraryById(id, libraryRequest)).thenReturn(libraryResponse);

        HttpResponse<LibraryResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.PUT("/api/v1/library/"+id, libraryRequest),
                LibraryResponse.class
        );

        assertEquals(libraryRequest.getName(), response.body().getName());
        assertEquals(libraryRequest.getEstablishDate(), response.body().getEstablishDate());
        assertTrue(response.body().isActive());
    }

    @Test
    void test_delete_by_id_should_delete_library_and_return_204() {
        UUID id = UUID.randomUUID();

        doNothing().when(libraryService).deleteLibraryDetailsById(id);

        HttpResponse<Void> response = httpClient.toBlocking().exchange(
                HttpRequest.DELETE("/api/v1/library/"+id),
                Void.class
        );

        assertEquals(204, response.getStatus().getCode());
        verify(libraryService, times(1)).deleteLibraryDetailsById(id);

    }
}
