package com.micronaut.h2.dto;

import com.micronaut.h2.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {
    private String studentName;
    private int age;
    private String stream;

    public static Student toStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentName(studentRequest.getStudentName());
        student.setAge(studentRequest.getAge());
        student.setStream(studentRequest.getStream());
        return student;
    }
}
