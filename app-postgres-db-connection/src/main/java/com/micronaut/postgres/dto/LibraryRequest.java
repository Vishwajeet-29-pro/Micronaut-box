package com.micronaut.postgres.dto;

import com.micronaut.postgres.model.Library;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Serdeable
public class LibraryRequest {
    private String name;
    private String location;
    private LocalDate establishDate;
    private boolean isActive;

    public static Library toLibrary(LibraryRequest libraryRequest) {
        Library library = new Library();
        library.setName(libraryRequest.getName());
        library.setLocation(libraryRequest.getLocation());
        library.setEstablishDate(libraryRequest.getEstablishDate());
        library.setActive(libraryRequest.isActive());

        return library;
    }
}
