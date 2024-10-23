package com.example.ppro3project.service;

import com.example.ppro3project.model.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DriverServiceImpl implements DriverService {

    ArrayList<Driver> cars = new ArrayList<>();

    @Override
    public ArrayList<Driver> getAllDrivers() {
        return cars;
    }

    @Override
    public Driver getDriverById(int id) {
        if (id >= 0 && id < cars.size())
        {
            return cars.get(id);
        }
        return null;
    }

    @Override
    public void deleteDriverById(int id) {
        if (id >= 0 && id < cars.size()) {
            cars.remove(id);
        }
    }

    @Override
    public void saveDriver(Driver car) {
        if (car.getId() > -1)
        {
            cars.remove(car.getId());
        }
        cars.add(car);
    }

}
