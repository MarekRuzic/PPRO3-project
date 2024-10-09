package com.example.ppro3project.service;

import com.example.ppro3project.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CarService {

    ArrayList<Car> getAllCars();
    Car getCarById(int id);
    void deleteCarById(int id);
    void saveCar(Car car);
}
