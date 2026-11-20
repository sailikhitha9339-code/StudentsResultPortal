package com.studentportal.controller;

import com.studentportal.model.Mark;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/marks")
public class MarkServlet extends HttpServlet {

    private MarkController markController;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext());

        markController = context.getBean(MarkController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Mark> marks = markController.getAllMarks();
        request.setAttribute("marks", marks);

        request.getRequestDispatcher("/marks.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String action = request.getParameter("action");

            boolean success = false;

            if ("add".equals(action)) {

                int studentId = Integer.parseInt(
                        request.getParameter("studentId"));

                int subjectId = Integer.parseInt(
                        request.getParameter("subjectId"));

                BigDecimal marksObtained = new BigDecimal(
                        request.getParameter("marksObtained"));

                Mark mark = new Mark(
                        0, studentId, subjectId, marksObtained);

                success = markController.addMark(mark);

            } else if ("update".equals(action)) {

                int markId = Integer.parseInt(
                        request.getParameter("markId"));

                BigDecimal marksObtained = new BigDecimal(
                        request.getParameter("marksObtained"));

                Mark mark = new Mark();
                mark.setMarkId(markId);
                mark.setMarksObtained(marksObtained);

                success = markController.updateMark(mark);

            } else if ("delete".equals(action)) {

                int markId = Integer.parseInt(
                        request.getParameter("markId"));

                success = markController.deleteMark(markId);
            }

            if (success) {
                response.sendRedirect(
                        request.getContextPath() + "/marks");
            } else {
                request.setAttribute(
                        "errorMessage", "Operation could not be completed.");
                doGet(request, response);
            }

        } catch (Exception e) {

            request.setAttribute(
                    "errorMessage", "Error: " + e.getMessage());

            doGet(request, response);
        }
    }
}
