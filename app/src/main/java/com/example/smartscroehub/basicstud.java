package com.example.smartscroehub;

public class basicstud {
    String student_name;
    String student_mobile;
    String  student_prn;
    String  student_email;
    String student_uid;
    String student_branch;
    String student_year;
    String date;


    public basicstud() {
    }

    public basicstud(String student_name, String student_mobile, String student_prn, String student_email, String student_uid, String student_branch, String student_year, String date) {
        this.student_name = student_name;
        this.student_mobile = student_mobile;
        this.student_prn = student_prn;
        this.student_email = student_email;
        this.student_uid = student_uid;
        this.student_branch = student_branch;
        this.student_year = student_year;
        this.date = date;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_mobile() {
        return student_mobile;
    }

    public void setStudent_mobile(String student_mobile) {
        this.student_mobile = student_mobile;
    }

    public String getStudent_prn() {
        return student_prn;
    }

    public void setStudent_prn(String student_prn) {
        this.student_prn = student_prn;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_uid() {
        return student_uid;
    }

    public void setStudent_uid(String student_uid) {
        this.student_uid = student_uid;
    }

    public String getStudent_branch() {
        return student_branch;
    }

    public void setStudent_branch(String student_branch) {
        this.student_branch = student_branch;
    }

    public String getStudent_year() {
        return student_year;
    }

    public void setStudent_year(String student_year) {
        this.student_year = student_year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
