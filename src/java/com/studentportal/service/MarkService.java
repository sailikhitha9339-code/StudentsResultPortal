package com.studentportal.service;

import com.studentportal.dao.MarkDAO;
import com.studentportal.model.Mark;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkService {

    private final MarkDAO markDAO;

    @Autowired
    public MarkService(MarkDAO markDAO) {
        this.markDAO = markDAO;
    }

    // Add a mark
    public boolean addMark(Mark mark) {
        return markDAO.addMark(mark);
    }

    // Display all marks
    public List<Mark> getAllMarks() {
        return markDAO.getAllMarks();
    }

    // Update a mark
    public boolean updateMark(Mark mark) {
        return markDAO.updateMark(mark);
    }

    // Delete a mark
    public boolean deleteMark(int markId) {
        return markDAO.deleteMark(markId);
    }
}

