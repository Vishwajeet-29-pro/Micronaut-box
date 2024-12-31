package com.micronaut.postgres.service;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.model.Library;
import com.micronaut.postgres.repository.LibraryRepository;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class LibraryServiceTest {

    @Inject
    private LibraryService libraryService;
    @Inject
    private LibraryRepository libraryRepository;

    @MockBean(LibraryRepository.class)
    @Replaces(LibraryRepository.class)
    LibraryRepository mockLibraryRepository() {
        return mock(LibraryRepository.class);
    }

    private LibraryRequest libraryRequest;
    private Library library;

    @BeforeEach
    public void setup() {
        libraryRequest = new LibraryRequest("Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);
        library = new Library(UUID.randomUUID(), "Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);
    }

    @Test
    void test_add_library_details_should_return_library_response() {
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        LibraryResponse libraryResponse = libraryService.addLibraryDetails(libraryRequest);

        assertNotNull(libraryResponse);
        assertEquals("Central Library", libraryResponse.getName());
        assertEquals("SB road, Pune", libraryResponse.getLocation());
        assertEquals(LocalDate.of(2004, 1, 1), libraryResponse.getEstablishDate());
    }
}