package com.example.ppro3project.controller;

import com.example.ppro3project.model.Project;
import com.example.ppro3project.model.Registration;
import com.example.ppro3project.service.ProjectService;
import com.example.ppro3project.service.RegistrationService;
import com.example.ppro3project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final ProjectService projectService;
    private final UserService userService;
    private final RegistrationService registrationService;
    private Project project = null;

    public RegistrationController(ProjectService projectService, UserService userService, RegistrationService registrationService) {
        this.projectService = projectService;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String list(Model model, Principal principal) {
        model.addAttribute("registrations", registrationService.getAllRegistrationsForUser(principal.getName()));
        return "registration_list";
    }

    @GetMapping("/create/{id}")
    public String create(Model model, @PathVariable long id) {
        this.project = projectService.getProjectById(id);
        if (project == null) {
            return "redirect:/registrations";
        }
        model.addAttribute("project", project);
        model.addAttribute("registration", new Registration());
        model.addAttribute("edit", false);
        return "registration_create";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable long id) {
        projectService.deleteProjectById(id);
        return "redirect:/registrations/";
    }

    @PostMapping("/save")
    public String save(@Valid Registration registration, BindingResult bindingResult,
                       Model model, Principal principal) {

        /*if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "registration_create";
        }*/

        registration.setProject(this.project);
        registration.setUser(userService.findByUsername(principal.getName()));
        registration.setDate(Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        registrationService.saveRegistration(registration);
        return "redirect:/registrations/";
    }
}
