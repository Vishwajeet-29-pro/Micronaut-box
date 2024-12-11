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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void testListAllStudentShouldReturnListOfStudent() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<StudentResponse> studentResponses = studentService.findAllStudents();

        assertNotNull(studentResponses);
        assertEquals(1, studentResponses.size());
        assertEquals("Harvey", studentResponses.getFirst().getStudentName());
        assertEquals(22, studentResponses.getFirst().getAge());
    }

    @Test
    void testFindByIdShouldReturnStudentDetail() {
        Long id = 1L;
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        StudentResponse studentResponse = studentService.findStudentById(id);

        assertNotNull(studentResponse);
        assertEquals("Harvey", studentResponse.getStudentName());
        assertEquals(22, studentResponse.getAge());
        assertEquals("Computer", studentResponse.getStream());
    }

    @Test
    void testUpdateStudentByIdShouldUpdateStudent() {
        Long id = 1L;
        StudentRequest updateStudent = new StudentRequest("Harvey Spector", 23, "Computer Science");
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        student.setStudentName("Harvey Spector");
        student.setAge(23);
        student.setStream("Computer Science");
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponse response = studentService.updateStudentDetailsById(id, updateStudent);

        assertNotNull(response);
        assertEquals("Harvey Spector", response.getStudentName());
        assertEquals(23, response.getAge());
        assertEquals("Computer Science", response.getStream());
    }

    @Test
    void testDeleteStudentByIdShouldDeleteStudent() {
        Long id = 1L;
        when(studentRepository.existsById(id)).thenReturn(true);

        studentService.deleteStudentById(id);

        verify(studentRepository, times(1)).deleteById(id);
    }
}
