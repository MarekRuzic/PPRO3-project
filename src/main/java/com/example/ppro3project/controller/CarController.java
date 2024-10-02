package com.example.ppro3project.controller;

import com.example.ppro3project.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    List<Car> cars = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model) {
        cars.add(new Car("4J4 2020", "Modrá", 20.5f, 5));
        model.addAttribute("cars", cars);
        return "list";
    }
}
