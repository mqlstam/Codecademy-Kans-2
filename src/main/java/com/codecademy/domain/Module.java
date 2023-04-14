package com.codecademy.domain;

public class Module {
    private int followNumber; // primary key
    private int contentID;
    private String moduleTitle;
    private Float version;
    private String contactPersonEmail;
    private String courseName;

    // Constructor
    public Module( int followNumber, String moduleTitle, int contentID, Float version, String courseName, String contactPersonEmail) {
        this.followNumber = followNumber;
        this.contentID = contentID;
        this.moduleTitle = moduleTitle;
        this.version = version;
        this.contactPersonEmail = contactPersonEmail;
        this.courseName = courseName;
    }

    public Module(){};

    // Getters and setters
    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }

    public int getContentId() {
        return contentID;
    }

    public void setContentId(int content) {
        this.contentID = content;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

