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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void test_retrieve_all_libraries_should_return_list_of_libraries() {
        when(libraryRepository.findAll()).thenReturn(List.of(library));

        List<LibraryResponse> libraryResponses = libraryService.findAllLibraries();

        assertNotNull(libraryResponses);
        assertEquals(1, libraryResponses.size());
        assertEquals("Central Library", libraryResponses.getFirst().getName());
        assertEquals("SB road, Pune", libraryResponses.getFirst().getLocation());
    }

    @Test
    void test_find_library_by_id_should_return_library_response() {
        when(libraryRepository.findById(any(UUID.class))).thenReturn(Optional.of(library));

        LibraryResponse libraryResponse = libraryService.getLibraryById(UUID.randomUUID());

        assertNotNull(libraryResponse);
        assertEquals("Central Library", libraryResponse.getName());
        assertEquals("SB road, Pune", libraryResponse.getLocation());
    }

    @Test
    void test_update_by_id_should_update_library_details() {
        UUID id = UUID.randomUUID();
        LibraryRequest updateRequest = new LibraryRequest("Eastern Library", "Patil Nagar, Pune", LocalDate.of(2005, 12, 1), false);
        when(libraryRepository.findById(id)).thenReturn(Optional.of(library));
        library.setName("Eastern Library");
        library.setLocation("Patil Nagar, Pune");
        library.setEstablishDate(LocalDate.of(2005, 12, 1));
        library.setActive(false);
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        LibraryResponse libraryResponse = libraryService.updateLibraryById(id, updateRequest);

        assertEquals(library.getName(), libraryResponse.getName());
        assertEquals(library.getLocation(), libraryResponse.getLocation());
        assertFalse(libraryResponse.isActive());
    }

    @Test
    void test_delete_by_id_should_delete_library() {
        UUID id = UUID.randomUUID();
        when(libraryRepository.existsById(id)).thenReturn(true);

        libraryService.deleteLibraryDetailsById(id);
        verify(libraryRepository, times(1)).deleteById(id);
    }
}