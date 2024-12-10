package com.micronaut.h2.dto;

import com.micronaut.h2.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String studentName;
    private int age;
    private String stream;

    public static StudentResponse toStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(), student.getStudentName(),
                student.getAge(), student.getStream()
        );
    }
}
