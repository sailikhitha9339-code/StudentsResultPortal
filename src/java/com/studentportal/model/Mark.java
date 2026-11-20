package com.studentportal.model;

import java.math.BigDecimal;

public class Mark {

    private int markId;
    private int studentId;
    private int subjectId;
    private BigDecimal marksObtained;

    public Mark() {
    }

    public Mark(int markId, int studentId,
            int subjectId, BigDecimal marksObtained) {

        this.markId = markId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.marksObtained = marksObtained;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(BigDecimal marksObtained) {
        this.marksObtained = marksObtained;
    }
}
