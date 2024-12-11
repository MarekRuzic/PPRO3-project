package com.example.ppro3project.service;

import com.example.ppro3project.model.Project;
import com.example.ppro3project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProjectById(long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
        }
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }
}
