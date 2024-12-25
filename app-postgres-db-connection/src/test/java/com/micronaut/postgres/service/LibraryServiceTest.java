package com.micronaut.postgres.service;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.repository.LibraryRepository;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@MicronautTest
class LibraryServiceTest {

    @Inject
    private LibraryService libraryService;
    @Inject
    private LibraryRepository libraryRepository;

    @MockBean
    LibraryRepository mockLibraryRepository() {
        return mock(LibraryRepository.class);
    }

    private LibraryRequest libraryRequest;
    private LibraryResponse libraryResponse;

    @BeforeEach
    public void setup() {
        libraryRequest = new LibraryRequest("Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);
        libraryResponse = new LibraryResponse(UUID.randomUUID(), "Central Library", "SB road, Pune", LocalDate.of(2004, 1, 1), true);
    }
}