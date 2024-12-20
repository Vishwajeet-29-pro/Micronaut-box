package com.micronaut.h2.controller;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.model.Student;
import com.micronaut.h2.repository.StudentRepository;
import com.micronaut.h2.service.StudentService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
@SerdeImport(StudentRequest.class)
class StudentControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Inject
    StudentService studentService;

    @MockBean(StudentService.class)
    StudentService mockStudentService() {
        return mock(StudentService.class);
    }


    @Test
    void test_create_student_should_create_student_return_created() {
        StudentRequest studentRequest = new StudentRequest("Robin",22, "Computer");
        StudentResponse studentResponse = new StudentResponse(1L, "Robin", 22, "Computer");

        when(studentService.createStudent(studentRequest)).thenReturn(studentResponse);

        HttpResponse<StudentResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.POST("/api/v1/students", studentRequest),
                StudentResponse.class
        );

        assertEquals(201, response.getStatus().getCode());
        assertNotNull(response.body());
        assertEquals("Robin", response.body().getStudentName());
        assertEquals(22, response.body().getAge());
    }

    @Test
    void test_get_all_student_should_return_list_of_student_response() {
        List<StudentResponse> studentResponses = List.of(
                new StudentResponse(1L, "Robin", 22, "Computer"),
                new StudentResponse(2L, "Katappa", 23, "Physics")
        );

        when(studentService.findAllStudents()).thenReturn(studentResponses);

        HttpResponse<List<StudentResponse>> response = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/students"),
                Argument.listOf(StudentResponse.class)

        );

        assertEquals(2, response.body().size());
        assertNotNull(response.body());
        assertEquals("Robin", response.body().getFirst().getStudentName());
        assertEquals(23, response.body().get(1).getAge());
        assertEquals("Physics", response.body().getLast().getStream());
    }

    @Test
    void test_get_by_id_should_return_student_response() {
        StudentResponse studentResponse = new StudentResponse(1L, "Robin", 22, "Computer");
        long studentId = 1L;
        when(studentService.findStudentById(anyLong())).thenReturn(studentResponse);

        HttpResponse<StudentResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/students/" + studentId),
                StudentResponse.class
        );

        assertEquals(200, response.getStatus().getCode());
        assertNotNull(response);
        assertEquals("Robin", response.body().getStudentName());
        assertEquals("Computer", response.body().getStream());
    }


}