package com.example.impl_ss10_demo_haitt.controllers;

import com.example.impl_ss10_demo_haitt.models.Student;
import com.example.impl_ss10_demo_haitt.services.IStudentService;
import com.example.impl_ss10_demo_haitt.services.impl.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet (name = "StudentController", value = "/student")
public class StudentControllers  extends HttpServlet {
    private static IStudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                 req.getRequestDispatcher("/student/create.jsp").forward(req,resp);
                 break;
            case "edit":
                editShowForm(req,resp);
                break;
            case "search":
                searchByName(req,resp);
            default:
                List<Student> students = studentService.findAll();
                req.setAttribute("students",students);
                req.getRequestDispatcher("student/list.jsp").forward(req,resp);
        }

    }

    private void editShowForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        Student student = studentService.findById(id);
        RequestDispatcher dispatcher;
        if (student == null){
            dispatcher =req.getRequestDispatcher("/student/error-404.jsp");
        } else {
            req.setAttribute("student", student);
            dispatcher = req.getRequestDispatcher("/student/edit.jsp");
        }
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action ==null){
            action = "";
        }
        switch (action){
            case "create":
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                Float points = Float.parseFloat(req.getParameter("point"));
                Student student = new Student(name,address,points);
                studentService.save(student);
                resp.sendRedirect("/student");
            case "delete":
                Long id = Long.parseLong(req.getParameter("id"));
                boolean isDelete = studentService.deleteById(id);
                if(isDelete){
                    resp.sendRedirect("/student");
                } else {
                    req.setAttribute("message", "xoa khong thanh cong");
                    List<Student> students = studentService.findAll();
                    req.setAttribute("students",students);
                    req.getRequestDispatcher("/student/list.jsp").forward(req,resp);
                }
            case "edit":
                updateStudent(req,resp);
                break;
            case "search":
                searchByName(req,resp);
                break;
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String search = req.getParameter("search");
        if(search != null){
            List<Student> students = studentService.searchByName(search);
            req.setAttribute("students",students);
        }
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("student/list.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String address  = req.getParameter("address");
        float point = Float.parseFloat(req.getParameter("point"));
        Student student = studentService.findById(id);
        RequestDispatcher dispatcher;
        if (student == null){
            dispatcher = req.getRequestDispatcher("student/error-404.jsp");
        } else  {
            student.setName(name);
            student.setAddress(address);
            student.setPoint(point);
            studentService.update(id,student);
            req.setAttribute("student", student);
            req.setAttribute("message","Successful");
            dispatcher = req.getRequestDispatcher("student/edit.jsp");
        }
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
