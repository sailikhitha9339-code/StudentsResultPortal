package com.studentportal.controller;

import com.studentportal.model.Student;
import com.studentportal.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentService.getAllStudents();
        request.setAttribute("students", students);

        request.getRequestDispatcher("/students.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            boolean result = false;

            if ("add".equals(action)) {

                Student student = new Student(
                        0,
                        request.getParameter("rollNumber"),
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("department"),
                        Integer.parseInt(request.getParameter("semester"))
                );

                result = studentService.addStudent(student);

            } else if ("update".equals(action)) {

                Student student = new Student(
                        Integer.parseInt(request.getParameter("studentId")),
                        "",
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("department"),
                        Integer.parseInt(request.getParameter("semester"))
                );

                result = studentService.updateStudent(student);

            } else if ("delete".equals(action)) {

                int studentId = Integer.parseInt(
                        request.getParameter("studentId"));

                result = studentService.deleteStudent(studentId);

            } else {
                request.setAttribute("errorMessage", "Invalid operation.");
                doGet(request, response);
                return;
            }

            if (result) {
                response.sendRedirect(
                        request.getContextPath() + "/students");
            } else {
                request.setAttribute("errorMessage",
                        "Operation failed. Check the details or database constraints.");
                doGet(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Please enter valid numeric values.");
            doGet(request, response);
        }
    }
}
