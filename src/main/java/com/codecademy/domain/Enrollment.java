package com.codecademy.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Enrollment {
    private LocalDateTime enrollmentDateTime;
    private String studentEmail;
    private String courseName;
    private int CertificateID;

    public Enrollment(String studentEmail, String courseName, LocalDateTime date) {
        this.studentEmail = studentEmail;
        this.courseName = courseName;
        this.enrollmentDateTime = date;
    }

    public Enrollment(String studentEmail, String courseName, LocalDateTime date, int CertificateID) {
        this.studentEmail = studentEmail;
        this.courseName = courseName;
        this.enrollmentDateTime = date;
        this.CertificateID = CertificateID;
    }

    public LocalDateTime getEnrollmentDateTime() {
        return enrollmentDateTime;
    }

    public void setEnrollmentDateTime(LocalDateTime enrollmentDateTime) {
        this.enrollmentDateTime = enrollmentDateTime;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCertificateID() {
        return CertificateID;
    }

    public void setCertificateID(int certificateID) {
        CertificateID = certificateID;
    }


    
}
