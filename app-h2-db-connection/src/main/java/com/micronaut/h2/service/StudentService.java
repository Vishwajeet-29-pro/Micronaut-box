package com.micronaut.h2.service;

import com.micronaut.h2.dto.StudentRequest;
import com.micronaut.h2.dto.StudentResponse;
import com.micronaut.h2.model.Student;
import com.micronaut.h2.repository.StudentRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = studentRepository.save(StudentRequest.toStudent(studentRequest));
        return StudentResponse.toStudentResponse(student);
    }

    public List<StudentResponse> findAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentResponse::toStudentResponse)
                .toList();
    }

    public StudentResponse findStudentById(Long id) {
        Student studentResponse = studentRepository.findById(id).orElseThrow();
        return StudentResponse.toStudentResponse(studentResponse);
    }

    public StudentResponse updateStudentDetailsById(Long id, StudentRequest updateStudent) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setStudentName(updateStudent.getStudentName());
        student.setAge(updateStudent.getAge());
        student.setStream(updateStudent.getStream());
        Student updatedStudent = studentRepository.save(student);
        return StudentResponse.toStudentResponse(updatedStudent);
    }
}
