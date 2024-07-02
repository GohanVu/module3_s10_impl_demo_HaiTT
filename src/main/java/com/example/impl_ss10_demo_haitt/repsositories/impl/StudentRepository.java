package com.example.impl_ss10_demo_haitt.repsositories.impl;

import com.example.impl_ss10_demo_haitt.models.Student;
import com.example.impl_ss10_demo_haitt.repsositories.IStudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentRepository implements IStudentRepository {
    private static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student(1L, "haiTT", "QN", 10.0f));
        students.add(new Student(2L, "Bảo Ngọc", "HN", 8.0f));
        students.add(new Student(3L, "Bảo Kỳ", "DN", 6.0f));
        students.add(new Student(5L, "Cook", "Bàn ăn", 2f));
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select  * from student");
            ResultSet resultSet = preparedStatement.executeQuery();
            long id;
            String name;
            String address;
            Float point;

            while (resultSet.next()) {
                id = resultSet.getLong("id");
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                point = resultSet.getFloat("point");
                students.add(new Student(id, name, address, point));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void save(Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("insert into student(name,address,point) values (?, ? , ?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setFloat(3, student.getPoint());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDelete;
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement("delete from student where id=?;");
            statement.setLong(1, id);
            isDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }


    @Override
    public Student findById(Long id) {
        for (Student student : this.findAll()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void editById(Long id, Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("update student set name = ?,address = ?, point =? where id = ?;");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setFloat(3, student.getPoint());
            preparedStatement.setLong(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
public List<Student> searchByName(String search) {
    List<Student> students1 = this.findAll();
    List<Student> result = new ArrayList<>();
    for(Student student : students1){
       if(student.getName().toLowerCase().contains(search.toLowerCase())){
           result.add(student);
       }
    }
    return result;
    }
}



