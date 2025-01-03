package com.micronaut.postgres.controller;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
}
