package com.codecademy.dao;

import java.util.List;

import com.codecademy.domain.Module;
import com.codecademy.domain.ModuleProgress;

import javafx.collections.ObservableList;

public interface ModuleDAO {
    ObservableList<Module> getAllModules();
    void addModule(Module module);
    void updateModule(Module module);
    void deleteModule(int followNumber);
    List<ModuleProgress> getAverageProgressPerModule(String courseName, String studentEmail);
    List<ModuleProgress> getAverageProgressPerModuleAllStudents(String courseName);
}