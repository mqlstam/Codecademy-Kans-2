package com.codecademy.domain;

import java.time.LocalDateTime;

public class Certificate{

private int certificateID;
private double grade;
private String employee;

    public Certificate(int certificateID, double grade, String employee) {
        this.certificateID = certificateID;
        this.grade = grade;
        this.employee = employee;
    }

    public Certificate(double grade, String employee) {
        this.grade = grade;
        this.employee = employee;
    }
    
    public int getCertificateID() {
        return certificateID;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    public String getEmployee() {
        return employee;
    }
    
    public void setEmployee(String employee) {
        this.employee = employee;
    }

}