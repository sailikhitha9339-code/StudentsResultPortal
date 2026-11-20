package com.studentportal.dao;

import com.studentportal.model.Subject;
import com.studentportal.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    // CREATE: Add a subject
    public boolean addSubject(Subject subject) {

        String sql = "INSERT INTO subjects "
                + "(subject_code, subject_name, max_marks) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSubjectCode());
            ps.setString(2, subject.getSubjectName());
            ps.setInt(3, subject.getMaxMarks());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding subject: " + e.getMessage());
            return false;
        }
    }

    // READ: Display all subjects
    public List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Subject subject = new Subject();

                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectCode(rs.getString("subject_code"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setMaxMarks(rs.getInt("max_marks"));

                subjects.add(subject);
            }

        } catch (SQLException e) {
            System.out.println("Error reading subjects: " + e.getMessage());
        }

        return subjects;
    }

    // UPDATE: Update subject details
    public boolean updateSubject(Subject subject) {

        String sql = "UPDATE subjects SET subject_code=?, "
                + "subject_name=?, max_marks=? WHERE subject_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getSubjectCode());
            ps.setString(2, subject.getSubjectName());
            ps.setInt(3, subject.getMaxMarks());
            ps.setInt(4, subject.getSubjectId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating subject: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Delete a subject
    public boolean deleteSubject(int subjectId) {

        String sql = "DELETE FROM subjects WHERE subject_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, subjectId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting subject: " + e.getMessage());
            return false;
        }
    }
}
