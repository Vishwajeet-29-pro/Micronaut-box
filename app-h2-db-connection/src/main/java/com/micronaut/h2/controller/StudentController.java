package com.micronaut.h2.controller;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.service.StudentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create a new Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Post
    public HttpResponse<StudentResponse> createStudent(@Body StudentRequest studentRequest) {
        StudentResponse response = studentService.createStudent(studentRequest);
        return HttpResponse.created(response);
    }

    @Operation(summary = "Retrieve all Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all students retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No students found",
                    content = @Content) })
    @Get
    public HttpResponse<List<StudentResponse>> getStudentsByList() {
        List<StudentResponse> studentResponses = studentService.findAllStudents();
        return HttpResponse.ok(studentResponses);
    }

    @Operation(summary = "Get a Student by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content) })
    @Get("/{id}")
    public HttpResponse<StudentResponse> getStudentById(@PathVariable Long id) {
        StudentResponse response = studentService.findStudentById(id);
        return HttpResponse.ok(response);
    }

    @Operation(summary = "Update an existing Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content) })
    @Put("/{id}")
    public HttpResponse<StudentResponse> updateStudentById(
            @PathVariable Long id, @Body StudentRequest studentRequest
    ) {
        StudentResponse studentResponse = studentService.updateStudentDetailsById(id, studentRequest);
        return HttpResponse.ok(studentResponse);
    }

    @Operation(summary = "Delete a Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully",
                    content = @Content) })
    @Delete("/{id}")
    public HttpResponse<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return HttpResponse.noContent();
    }
}
