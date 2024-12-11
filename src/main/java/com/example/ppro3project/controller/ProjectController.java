package com.example.ppro3project.controller;

import com.example.ppro3project.model.Project;
import com.example.ppro3project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("projects", projectService.getAllProjects());
        return "projects_list";
    }
/*
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            model.addAttribute("project", project);
            return "project_detail";
        }
        return "redirect:/";
    }*/
}
