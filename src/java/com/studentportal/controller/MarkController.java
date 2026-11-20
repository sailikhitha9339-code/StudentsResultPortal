package com.studentportal.controller;

import com.studentportal.model.Mark;
import com.studentportal.service.MarkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    public boolean addMark(Mark mark) {
        return markService.addMark(mark);
    }

    public List<Mark> getAllMarks() {
        return markService.getAllMarks();
    }

    public boolean updateMark(Mark mark) {
        return markService.updateMark(mark);
    }

    public boolean deleteMark(int markId) {
        return markService.deleteMark(markId);
    }
}
