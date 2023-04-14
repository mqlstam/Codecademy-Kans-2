
package com.codecademy.dao;

import java.util.List;

import com.codecademy.domain.Course;
import javafx.collections.ObservableList;

public interface CourseDAO {
    ObservableList<Course> getCourses();
    ObservableList<String> getAllCourseNames();
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Course course) throws Exception;
    List<String> getTop3CertifiedCourses();
    public List<String> getRecommendedCourses(String selectedCourse);
    public int getNumCompletedCourses(String courseName);
    public List<Course> getCoursesByStudentEmail(String emailAddress);
}