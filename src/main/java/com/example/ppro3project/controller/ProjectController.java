package com.example.ppro3project.controller;

import com.example.ppro3project.model.Feedback;
import com.example.ppro3project.model.Project;
import com.example.ppro3project.model.User;
import com.example.ppro3project.service.FeedbackService;
import com.example.ppro3project.service.ProjectService;
import com.example.ppro3project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.FeatureDescriptor;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final FeedbackService feedbackService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService, FeedbackService feedbackService) {
        this.projectService = projectService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("edit", false);
        return "project_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return "redirect:/";
        }
        model.addAttribute("project", project);
        model.addAttribute("edit", true);
        return "project_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Project project, BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "project_edit";
        }
        String username = principal.getName();
        User organizer = userService.findByUsername(username);

        // Nastavit organizátora projektu
        project.setOrganizer(organizer);

        // Uložit projekt do databáze
        projectService.saveProject(project);

        return "redirect:/projects/"; // Přesměrování na seznam projektů
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id, Principal principal) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return "redirect:/";
        }
        model.addAttribute("project", project);
        List<Feedback> feedbacks = feedbackService.getAllFeedbacksForProject(id);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("username", principal.getName());
        return "project_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects/";
    }
}
