package com.micronaut.postgres.service;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.repository.LibraryRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Override
    public LibraryResponse addLibraryDetails(LibraryRequest libraryRequest) {
        return null;
    }

    @Override
    public List<LibraryResponse> findAllLibraries() {
        return List.of();
    }

    @Override
    public LibraryResponse getLibraryById(UUID id) {
        return null;
    }

    @Override
    public LibraryResponse updateLibraryById(UUID id, LibraryRequest libraryRequest) {
        return null;
    }

    @Override
    public void deleteLibraryDetailsById(UUID id) {

    }
}
