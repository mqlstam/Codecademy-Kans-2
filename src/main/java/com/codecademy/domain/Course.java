package com.codecademy.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String courseTopic;
    private String CourseIntroText;
    private String courseTag;
    private Difficulty difficulty;

    public Course(String courseName, String courseTopic, String courseIntroText, String courseTag, Difficulty difficulty) {
        this.courseName = courseName;
        this.courseTopic = courseTopic;
        this.CourseIntroText = courseIntroText;
        this.courseTag = courseTag;
        this.difficulty = difficulty;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(String courseTopic) {
        this.courseTopic = courseTopic;
    }

    public String getCourseIntroText() {
        return CourseIntroText;
    }

    public void setCourseIntroText(String courseIntroText) {
        CourseIntroText = courseIntroText;
    }

    public String getCourseTag() {
        return courseTag;
    }

    public void setCourseTag(String courseTag) {
        this.courseTag = courseTag;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return courseName;
    }
    
}
