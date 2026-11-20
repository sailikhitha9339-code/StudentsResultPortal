
package com.studentportal.controller;

import com.studentportal.model.Subject;
import com.studentportal.service.SubjectService;
import java.util.List;

public class SubjectController {

    private SubjectService subjectService = new SubjectService();

    public boolean addSubject(Subject subject) {
        return subjectService.addSubject(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    public boolean updateSubject(Subject subject) {
        return subjectService.updateSubject(subject);
    }

    public boolean deleteSubject(int subjectId) {
        return subjectService.deleteSubject(subjectId);
    }
}
