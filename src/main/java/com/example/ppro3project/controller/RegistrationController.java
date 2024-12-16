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

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final ProjectService projectService;
    private final UserService userService;
    private final RegistrationService registrationService;

    public RegistrationController(ProjectService projectService, UserService userService, RegistrationService registrationService) {
        this.projectService = projectService;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("registrations", registrationService.getAllRegistrations());
        return "registration_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
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
    public String save(@Valid Registration registration, BindingResult bindingResult, Model model, Principal principal) {
        /*if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "registration_create";
        }*/
        registrationService.saveRegistration(registration);
        return "redirect:/registrations/";
    }
}
