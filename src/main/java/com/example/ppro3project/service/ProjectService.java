package com.example.ppro3project.service;

import com.example.ppro3project.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();
    Project getProjectById(long id);
    void deleteProjectById(long id);
}
