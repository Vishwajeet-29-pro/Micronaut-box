package com.micronaut.h2.repository;

import com.micronaut.h2.model.Student;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
