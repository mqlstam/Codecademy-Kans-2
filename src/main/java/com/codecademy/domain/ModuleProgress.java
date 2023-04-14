package com.codecademy.domain;

public class ModuleProgress {
    private int followNumber;
    private String moduleTitle;
    private double progress;

    public ModuleProgress(int followNumber, String moduleTitle, double progress) {
        this.followNumber = followNumber;
        this.moduleTitle = moduleTitle;
        this.progress = progress;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public double getProgress() {
        return progress;
    }
}
