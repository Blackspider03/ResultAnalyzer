package com.example.smartscroehub;

public class progress {
    String progress;
    String subject;

    public progress() {
    }

    public progress(String progress, String subject) {
        this.progress = progress;
        this.subject = subject;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
