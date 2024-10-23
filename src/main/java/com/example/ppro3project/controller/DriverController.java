package com.example.ppro3project.controller;

import com.example.ppro3project.model.Driver;
import com.example.ppro3project.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService carService;

    @Autowired
    public DriverController(DriverService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        //cars.add(new Driver("4J4 2020", "Modrá", 20.5f, 5));
        model.addAttribute("cars", carService.getAllDrivers());
        return "car_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Driver car = carService.getDriverById(index);
        if (car != null) {
            model.addAttribute("car", car);
            return "car_detail";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        carService.deleteDriverById(index);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Driver());
        model.addAttribute("edit", false);
        return "car_edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        Driver car = carService.getDriverById(index);
        if (car != null) {
            car.setId(index);
            model.addAttribute("car", car);
            model.addAttribute("edit", true);
            return "car_edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Driver car) {
        carService.saveDriver(car);
        return "redirect:/";
    }
}
