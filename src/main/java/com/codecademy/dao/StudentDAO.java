package com.codecademy.dao;

import java.util.List;

import com.codecademy.domain.Student;
import javafx.collections.ObservableList;

public interface StudentDAO {
    ObservableList<Student> getStudents();
    ObservableList<String> getAllStudentEmails();
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
    List<String> getCertificatesByEmail(String string);
    public void getProgressPerModuleForAccount(String emailAddress, String courseName);
}
