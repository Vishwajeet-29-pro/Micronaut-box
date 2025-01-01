package com.micronaut.postgres.service;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.model.Library;
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
        Library library = libraryRepository.save(LibraryRequest.toLibrary(libraryRequest));
        return LibraryResponse.toResponse(library);
    }

    @Override
    public List<LibraryResponse> findAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(LibraryResponse::toResponse)
                .toList();
    }

    @Override
    public LibraryResponse getLibraryById(UUID id) {
        Library response = libraryRepository.findById(id).orElseThrow();
        return LibraryResponse.toResponse(response);
    }

    @Override
    public LibraryResponse updateLibraryById(UUID id, LibraryRequest libraryRequest) {
        Library updateLibrary = libraryRepository.findById(id).orElseThrow();
        updateLibrary.setName(libraryRequest.getName());
        updateLibrary.setLocation(libraryRequest.getLocation());
        updateLibrary.setEstablishDate(libraryRequest.getEstablishDate());
        updateLibrary.setActive(libraryRequest.isActive());
        Library updatedLibrary = libraryRepository.save(updateLibrary);

        return LibraryResponse.toResponse(updatedLibrary);
    }

    @Override
    public void deleteLibraryDetailsById(UUID id) {
        if (!libraryRepository.existsById(id)) {
            throw new IllegalArgumentException("Library with id "+id+" not found");
        }
        libraryRepository.deleteById(id);
    }
}
