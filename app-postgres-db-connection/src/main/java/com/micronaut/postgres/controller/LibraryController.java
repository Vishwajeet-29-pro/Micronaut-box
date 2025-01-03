package com.micronaut.postgres.controller;

import com.micronaut.postgres.dto.LibraryRequest;
import com.micronaut.postgres.dto.LibraryResponse;
import com.micronaut.postgres.service.LibraryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Controller("/api/v1/library")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class LibraryController {
    private final LibraryService libraryService;


    @Operation(summary = "Add a new Library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Library Details Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibraryRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Post
    public HttpResponse<LibraryResponse> createLibrary(@Body LibraryRequest libraryRequest) {
        return HttpResponse.created(libraryService.addLibraryDetails(libraryRequest));
    }

    @Operation(summary = "Retrieve all Libraries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all Libraries retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibraryResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No Libraries found",
                    content = @Content) })
    @Get
    public HttpResponse<List<LibraryResponse>> retrieveAllLibraries() {
        return HttpResponse.ok(libraryService.findAllLibraries());
    }

    @Operation(summary = "Get a Library Details by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Library",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibraryResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content) })
    @Get("/{id}")
    public HttpResponse<LibraryResponse> retrieveLibraryById(@PathVariable UUID id) {
        return HttpResponse.ok(libraryService.getLibraryById(id));
    }

    @Operation(summary = "Update an existing Library by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library Details updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibraryResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content) })
    @Put("/{id}")
    public HttpResponse<LibraryResponse> updateLibraryById(@PathVariable UUID id, @Body LibraryRequest libraryRequest) {
        return HttpResponse.ok(libraryService.updateLibraryById(id, libraryRequest));
    }

    @Operation(summary = "Delete a Library Details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Library deleted successfully",
                    content = @Content) })
    @Delete("/{id}")
    public HttpResponse<Void> deleteLibraryById(@PathVariable UUID id) {
        libraryService.deleteLibraryDetailsById(id);
        return HttpResponse.noContent();
    }
}
