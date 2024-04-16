package com.example.smartscroehub;

public class staff {
    String Prof_name;
    String Prof_mobile;
    String email;
    String department;
    String uri;

    public staff() {
    }

    public staff(String prof_name, String prof_mobile, String email, String department, String uri) {
        Prof_name = prof_name;
        Prof_mobile = prof_mobile;
        this.email = email;
        this.department = department;
        this.uri = uri;
    }

    public String getProf_name() {
        return Prof_name;
    }

    public void setProf_name(String prof_name) {
        Prof_name = prof_name;
    }

    public String getProf_mobile() {
        return Prof_mobile;
    }

    public void setProf_mobile(String prof_mobile) {
        Prof_mobile = prof_mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
