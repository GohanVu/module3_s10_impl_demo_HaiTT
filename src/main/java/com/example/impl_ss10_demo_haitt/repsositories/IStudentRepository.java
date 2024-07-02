package com.example.impl_ss10_demo_haitt.repsositories;

import com.example.impl_ss10_demo_haitt.models.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();

    void save(Student student);

    boolean deleteById(Long id);

    Student findById(Long id);

    void editById(Long id, Student student);
}
