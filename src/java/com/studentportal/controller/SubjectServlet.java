package com.studentportal.controller;

import com.studentportal.model.Subject;
import com.studentportal.service.SubjectService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {

    private SubjectService subjectService = new SubjectService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Subject> subjects = subjectService.getAllSubjects();

        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("/subjects.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        try {

            boolean result = false;

            if ("add".equals(action)) {

                Subject subject = new Subject(
                    0,
                    request.getParameter("subjectCode"),
                    request.getParameter("subjectName"),
                    Integer.parseInt(request.getParameter("maxMarks"))
                );

                result = subjectService.addSubject(subject);

            } else if ("update".equals(action)) {

                Subject subject = new Subject(
                    Integer.parseInt(request.getParameter("subjectId")),
                    request.getParameter("subjectCode"),
                    request.getParameter("subjectName"),
                    Integer.parseInt(request.getParameter("maxMarks"))
                );

                result = subjectService.updateSubject(subject);

            } else if ("delete".equals(action)) {

                int subjectId = Integer.parseInt(
                    request.getParameter("subjectId"));

                result = subjectService.deleteSubject(subjectId);

            } else {
                request.setAttribute("errorMessage",
                        "Invalid operation.");
                doGet(request, response);
                return;
            }

            if (result) {
                response.sendRedirect(
                    request.getContextPath() + "/subjects");
            } else {
                request.setAttribute("errorMessage",
                        "Operation failed. Check the details or database constraints.");
                doGet(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute("errorMessage",
                    "Please enter valid numeric values.");

            doGet(request, response);
        }
    }
}
