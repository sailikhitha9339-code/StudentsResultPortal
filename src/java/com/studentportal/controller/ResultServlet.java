package com.studentportal.controller;

import com.studentportal.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.*;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/result.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String rollNumber = request.getParameter("rollNumber");

        String sql = "SELECT s.student_id, s.roll_number, s.name, "
        + "s.department, s.semester, m.subject_id, "
        + "sub.subject_code, sub.subject_name, "
        + "sub.max_marks, m.marks_obtained "
                + "FROM students s "
                + "LEFT JOIN marks m ON s.student_id = m.student_id "
                + "LEFT JOIN subjects sub ON m.subject_id = sub.subject_id "
                + "WHERE s.roll_number = ?";

        List<Map<String, Object>> rows = new ArrayList<>();

        BigDecimal totalObtained = BigDecimal.ZERO;
        BigDecimal totalMaximum = BigDecimal.ZERO;
        boolean passed = true;
        boolean studentFound = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNumber);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    studentFound = true;

                    request.setAttribute("studentName", rs.getString("name"));
                    request.setAttribute("rollNumber", rs.getString("roll_number"));
                    request.setAttribute("department", rs.getString("department"));
                    request.setAttribute("semester", rs.getInt("semester"));

                    int subjectId = rs.getInt("subject_id");

                    if (rs.wasNull()) {
                        continue;
                    }

                    int maxMarks = rs.getInt("max_marks");
                    BigDecimal obtained = rs.getBigDecimal("marks_obtained");

                    if (obtained == null) {
                        obtained = BigDecimal.ZERO;
                    }

                    Map<String, Object> row = new HashMap<>();
                    row.put("subjectCode", rs.getString("subject_code"));
                    row.put("subjectName", rs.getString("subject_name"));
                    row.put("maxMarks", maxMarks);
                    row.put("obtained", obtained);

                    rows.add(row);

                    totalObtained = totalObtained.add(obtained);
                    totalMaximum = totalMaximum.add(
                            BigDecimal.valueOf(maxMarks));

                    if (maxMarks <= 0 ||
                            obtained.compareTo(
                                    BigDecimal.valueOf(maxMarks)
                                            .multiply(new BigDecimal("0.35")))
                                    < 0) {
                        passed = false;
                    }
                }
            }

            request.setAttribute("studentFound", studentFound);
            request.setAttribute("resultRows", rows);
            request.setAttribute("totalObtained", totalObtained);
            request.setAttribute("totalMaximum", totalMaximum);

            if (!rows.isEmpty() && totalMaximum.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal percentage = totalObtained
                        .multiply(BigDecimal.valueOf(100))
                        .divide(totalMaximum, 2, RoundingMode.HALF_UP);

                request.setAttribute("percentage", percentage);
                request.setAttribute("status", passed ? "PASS" : "FAIL");

                String grade;
                if (percentage.compareTo(new BigDecimal("90")) >= 0) grade = "A+";
                else if (percentage.compareTo(new BigDecimal("80")) >= 0) grade = "A";
                else if (percentage.compareTo(new BigDecimal("70")) >= 0) grade = "B";
                else if (percentage.compareTo(new BigDecimal("60")) >= 0) grade = "C";
                else if (percentage.compareTo(new BigDecimal("50")) >= 0) grade = "D";
                else if (percentage.compareTo(new BigDecimal("35")) >= 0) grade = "E";
                else grade = "F";

                request.setAttribute("grade", grade);
            }

        } catch (Exception e) {
            request.setAttribute("errorMessage",
                    "Unable to generate result: " + e.getMessage());
        }

        request.getRequestDispatcher("/result.jsp")
                .forward(request, response);
    }
}
