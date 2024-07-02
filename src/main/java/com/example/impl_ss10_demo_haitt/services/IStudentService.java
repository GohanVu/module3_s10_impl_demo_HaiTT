package com.example.impl_ss10_demo_haitt.services;

import com.example.impl_ss10_demo_haitt.models.Student;

import java.util.List;

public interface  IStudentService {
    List<Student> findAll();

    void save(Student student);

    boolean deleteById(Long id);

    Student findById(Long id);

    List<Student> searchByName(String search);

    void update(Long id, Student student);
}
