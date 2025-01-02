package com.micronaut.postgres;

import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller("/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
}
