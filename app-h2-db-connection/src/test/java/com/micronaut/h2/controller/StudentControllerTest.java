package com.micronaut.h2.controller;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
@SerdeImport(StudentRequest.class)
class StudentControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void test_create_student_should_create_student_return_created() {
        StudentRequest studentRequest = new StudentRequest("Robin",22, "Computer");

        HttpResponse<StudentResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.POST("/api/v1/students", studentRequest),
                StudentResponse.class
        );

        assertEquals(201, response.getStatus().getCode());
        assertNotNull(response.body());
        assertEquals("Robin", response.body().getStudentName());
        assertEquals(22, response.body().getAge());
    }

}