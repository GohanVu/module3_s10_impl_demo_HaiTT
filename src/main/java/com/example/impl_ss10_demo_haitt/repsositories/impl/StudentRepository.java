package com.example.impl_ss10_demo_haitt.repsositories.impl;

import com.example.impl_ss10_demo_haitt.models.Student;
import com.example.impl_ss10_demo_haitt.repsositories.IStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentRepository implements IStudentRepository {
    private static List<Student> students;
    static {
        students = new ArrayList<>();
        students.add(new Student(1L,"haiTT", "QN", 10.0f));
        students.add(new Student(2L,"Bảo Ngọc", "HN", 8.0f));
        students.add(new Student(3L,"Bảo Kỳ", "DN", 6.0f));
        students.add(new Student(5L,"Cook", "Bàn ăn", 2f));
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void save(Student student) {
        student.setId(students.get(students.size()-1).getId()+1);
        students.add(student);
    }

    @Override
    public boolean deleteById(Long id) {
        for (Student student : students){
            if(student.getId().equals(id)){
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    @Override
    public Student findById(Long id) {
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }

    @Override
    public void editById(Long id, Student student) {
        for (Student student1 :students){
            if(student1.getId().equals(id)) {
                student1.setName(student.getName());
                student1.setAddress(student.getAddress());
                student1.setPoint(student.getPoint());
            }
        }
    }
}
