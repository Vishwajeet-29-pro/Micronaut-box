package com.micronaut.postgres.service;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;

import java.util.List;
import java.util.UUID;

public interface LibraryService {
    LibraryResponse addLibraryDetails(LibraryRequest libraryRequest);
    List<LibraryResponse> findAllLibraries();
    LibraryResponse getLibraryById(UUID id);
    LibraryResponse updateLibraryById(UUID id, LibraryRequest libraryRequest);
    void deleteLibraryDetailsById(UUID id);
}
