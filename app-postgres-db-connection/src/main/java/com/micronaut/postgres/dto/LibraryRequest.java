package com.micronaut.postgres.dto;

import com.micronaut.postgres.model.Library;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LibraryRequest {
    private String name;
    private String location;
    private LocalDate establishDate;
    private boolean isActive;

    public static Library toLibrary(LibraryRequest libraryRequest) {
        Library library = new Library();
        library.setName(libraryRequest.getName());
        library.setLocation(libraryRequest.getLocation());
        library.setDate(libraryRequest.getEstablishDate());
        library.setActive(libraryRequest.isActive());

        return library;
    }
}
