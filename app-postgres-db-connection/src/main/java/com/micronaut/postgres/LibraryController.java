package com.micronaut.postgres;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

@Controller("/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @Post
    public HttpResponse<LibraryResponse> createLibrary(@Body LibraryRequest libraryRequest) {
        return HttpResponse.created(libraryService.addLibraryDetails(libraryRequest));
    }
}
