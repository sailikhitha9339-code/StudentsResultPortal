package com.studentportal.service;

import com.studentportal.dao.StudentDAO;
import com.studentportal.model.Student;
import java.util.List;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    // Add student
    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Update student
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    // Delete student
    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }
}
