package com.example.ppro3project.controller;

import com.example.ppro3project.model.Feedback;
import com.example.ppro3project.model.Project;
import com.example.ppro3project.service.FeedbackService;
import com.example.ppro3project.service.ProjectService;
import com.example.ppro3project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final ProjectService projectService;
    private final UserService userService;
    private final FeedbackService feedbackService;
    private Project project = null;

    public FeedbackController(ProjectService projectService, UserService userService, FeedbackService feedbackService) {
        this.projectService = projectService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/create/{id}")
    public String create(Model model, @PathVariable long id) {
        this.project = projectService.getProjectById(id);
        if(project == null) {
            return "redirect:/projects/";
        }
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("edit", false);
        return "feedback_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            return "redirect:/";
        }
        model.addAttribute("feedback", feedback);
        model.addAttribute("edit", true);
        return "feedback_edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        feedbackService.deleteFeedbackById(id);
        return "redirect:/projects/detail/" + id;
    }

    @PostMapping("/save")
    public String save(@Valid Feedback feedback, BindingResult bindingResult, Model model, Principal principal) {
        feedback.setFeedbackProject(project);
        feedback.setFeedbackUser(userService.findByUsername(principal.getName()));
        feedback.setFeedbackDate(Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
        }
        feedbackService.saveFeedback(feedback);
        return "redirect:/projects/";
    }
}
