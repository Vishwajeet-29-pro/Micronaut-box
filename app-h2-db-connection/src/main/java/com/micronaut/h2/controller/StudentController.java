package com.micronaut.h2.controller;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.service.StudentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Post
    public HttpResponse<StudentResponse> createStudent(@Body StudentRequest studentRequest) {
        StudentResponse response = studentService.createStudent(studentRequest);
        return HttpResponse.created(response);
    }

    @Get
    public HttpResponse<List<StudentResponse>> getStudentsByList() {
        List<StudentResponse> studentResponses = studentService.findAllStudents();
        return HttpResponse.ok(studentResponses);
    }
}
