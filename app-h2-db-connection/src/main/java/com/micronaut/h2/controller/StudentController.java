package com.micronaut.h2.controller;

import com.micronaut.h2.service.StudentService;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

}
