package com.example.smartscroehub;

public class student1 {
    String maximum_marks;
    String obtained_marks;
    String subject;
    String testtype;

    public student1() {
    }

    public student1(String maximum_marks, String obtained_marks, String subject,String testtype) {
        this.maximum_marks = maximum_marks;
        this.obtained_marks = obtained_marks;
        this.subject = subject;
        this.testtype = testtype;
    }

    public String getMaximum_marks() {
        return maximum_marks;
    }

    public void setMaximum_marks(String maximum_marks) {
        this.maximum_marks = maximum_marks;
    }

    public String getObtained_marks() {
        return obtained_marks;
    }

    public void setObtained_marks(String obtained_marks) {
        this.obtained_marks = obtained_marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }
}
