package com.studentportal.dao;

import com.studentportal.model.Mark;
import com.studentportal.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MarkDAO {

    // CREATE: Add marks for a student
    public boolean addMark(Mark mark) {

        String sql = "INSERT INTO marks "
                + "(student_id, subject_id, marks_obtained) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mark.getStudentId());
            ps.setInt(2, mark.getSubjectId());
            ps.setBigDecimal(3, mark.getMarksObtained());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding marks: " + e.getMessage());
            return false;
        }
    }

    // READ: Display all marks
    public List<Mark> getAllMarks() {

        List<Mark> marks = new ArrayList<>();
        String sql = "SELECT * FROM marks";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Mark mark = new Mark();

                mark.setMarkId(rs.getInt("mark_id"));
                mark.setStudentId(rs.getInt("student_id"));
                mark.setSubjectId(rs.getInt("subject_id"));
                mark.setMarksObtained(
                        rs.getBigDecimal("marks_obtained"));

                marks.add(mark);
            }

        } catch (SQLException e) {
            System.out.println("Error reading marks: " + e.getMessage());
        }

        return marks;
    }

    // UPDATE: Update student marks
    public boolean updateMark(Mark mark) {

        String sql = "UPDATE marks SET marks_obtained=? "
                + "WHERE mark_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, mark.getMarksObtained());
            ps.setInt(2, mark.getMarkId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating marks: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Delete a marks record
    public boolean deleteMark(int markId) {

        String sql = "DELETE FROM marks WHERE mark_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, markId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting marks: " + e.getMessage());
            return false;
        }
    }
}
