package com.micronaut.postgres.dto;

import com.micronaut.postgres.model.Library;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LibraryResponse {

    private UUID id;
    private String name;
    private String location;
    private LocalDate establishDate;
    private boolean isActive;

    public static LibraryResponse toResponse(Library library) {
        return new LibraryResponse(
                library.getId(), library.getName(),
                library.getLocation(), library.getEstablishDate(),
                library.isActive()
        );
    }
}
