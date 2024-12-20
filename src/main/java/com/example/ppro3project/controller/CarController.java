package com.example.ppro3project.controller;

import com.example.ppro3project.model.Car;
import com.example.ppro3project.service.CarService;
import com.example.ppro3project.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final DriverService driverService;

    @Autowired
    public CarController(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {
        //cars.add(new Car("4J4 2020", "Modrá", 20.5f, 5));
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            model.addAttribute("car", car);
            return "car_detail";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        carService.deleteCarById(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "car_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            //car.setId(id);
            model.addAttribute("car", car);
            model.addAttribute("edit", true);
            model.addAttribute("drivers", driverService.getAllDrivers());
            return "car_edit";
        }
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("drivers", driverService.getAllDrivers());
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }
}
