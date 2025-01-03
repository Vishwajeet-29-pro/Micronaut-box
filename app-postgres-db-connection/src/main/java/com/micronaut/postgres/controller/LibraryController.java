package com.micronaut.postgres.controller;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Controller("/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @Post
    public HttpResponse<LibraryResponse> createLibrary(@Body LibraryRequest libraryRequest) {
        return HttpResponse.created(libraryService.addLibraryDetails(libraryRequest));
    }

    @Get
    public HttpResponse<List<LibraryResponse>> retrieveAllLibraries() {
        return HttpResponse.ok(libraryService.findAllLibraries());
    }

    @Get("/{id}")
    public HttpResponse<LibraryResponse> retrieveLibraryById(@PathVariable UUID id) {
        return HttpResponse.ok(libraryService.getLibraryById(id));
    }

    @Put("/{id}")
    public HttpResponse<LibraryResponse> updateLibraryById(@PathVariable UUID id, @Body LibraryRequest libraryRequest) {
        return HttpResponse.ok(libraryService.updateLibraryById(id, libraryRequest));
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteLibraryById(@PathVariable UUID id) {
        libraryService.deleteLibraryDetailsById(id);
        return HttpResponse.noContent();
    }
}
