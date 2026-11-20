
package com.studentportal.service;

import com.studentportal.model.Subject;
import com.studentportal.dao.SubjectDAO;

import java.util.List;

public class SubjectService {

    private SubjectDAO subjectDAO = new SubjectDAO();

    public boolean addSubject(Subject subject) {
        return subjectDAO.addSubject(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    public boolean updateSubject(Subject subject) {
        return subjectDAO.updateSubject(subject);
    }

    public boolean deleteSubject(int subjectId) {
        return subjectDAO.deleteSubject(subjectId);
    }
}
