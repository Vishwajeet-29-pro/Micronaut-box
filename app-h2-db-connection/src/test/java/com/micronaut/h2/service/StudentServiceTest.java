package com.micronaut.h2.service;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.model.Student;
import com.micronaut.h2.repository.StudentRepository;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class StudentServiceTest {

    @Inject
    StudentService studentService;

    @Inject
    StudentRepository studentRepository;

    @MockBean(StudentRepository.class)
    StudentRepository mockStudentRepository() {
        return mock(StudentRepository.class);
    }

    StudentRequest studentRequest;
    Student student;

    @BeforeEach
    public void setup() {
        studentRequest = new StudentRequest("Harvey", 22, "Computer");
        student = new Student(1L, "Harvey", 22, "Computer");
    }

    @Test
    void testCreateUserShouldCreateUser() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponse response = studentService.createStudent(studentRequest);

        assertNotNull(response);
        assertEquals("Harvey", response.getStudentName());
        assertEquals(22, response.getAge());
        assertEquals("Computer", response.getStream());
    }
}
