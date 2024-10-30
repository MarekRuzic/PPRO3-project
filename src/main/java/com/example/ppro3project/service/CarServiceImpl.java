package com.example.ppro3project.service;

import com.example.ppro3project.model.Car;
import com.example.ppro3project.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    CarRepository carRepository;
    ArrayList<Car> cars = new ArrayList<>();

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCarById(long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent())
        {
            carRepository.delete(car.get());
        }
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

}
