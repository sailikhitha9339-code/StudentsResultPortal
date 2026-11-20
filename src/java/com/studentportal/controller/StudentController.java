package com.studentportal.controller;

import com.studentportal.model.Student;
import com.studentportal.service.StudentService;
import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();

    public boolean addStudent(Student student) {
        return studentService.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    public boolean deleteStudent(int studentId) {
        return studentService.deleteStudent(studentId);
    }
}
