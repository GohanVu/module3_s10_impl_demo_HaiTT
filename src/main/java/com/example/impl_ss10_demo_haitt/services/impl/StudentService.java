package com.example.impl_ss10_demo_haitt.services.impl;

import com.example.impl_ss10_demo_haitt.models.Student;
import com.example.impl_ss10_demo_haitt.repsositories.IStudentRepository;
import com.example.impl_ss10_demo_haitt.repsositories.impl.StudentRepository;
import com.example.impl_ss10_demo_haitt.services.IStudentService;

import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService {
    private static IStudentRepository studentRepository = new StudentRepository() ;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public boolean deleteById(Long id) {

        return studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> searchByName(String search) {
        return  studentRepository.searchByName(search);
    }

    @Override
    public void update(Long id, Student student) {
       studentRepository.editById(id, student);
    }
}
