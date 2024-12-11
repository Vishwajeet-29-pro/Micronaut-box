package com.micronaut.h2.service;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.model.Student;
import com.micronaut.h2.repository.StudentRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = studentRepository.save(StudentRequest.toStudent(studentRequest));
        return StudentResponse.toStudentResponse(student);
    }
}
