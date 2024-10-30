package com.example.ppro3project.service;

import com.example.ppro3project.model.Driver;
import com.example.ppro3project.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;
    ArrayList<Driver> cars = new ArrayList<>();

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(long id) {
        /*if (id >= 0 && id < cars.size())
        {
            return cars.get(id);
        }
        return null;*/
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDriverById(long id) {
        /*if (id >= 0 && id < cars.size()) {
            cars.remove(id);
        }*/
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            driverRepository.delete(driver.get());
        }
    }

    @Override
    public void saveDriver(Driver car) {
        /*if (car.getId() > -1)
        {
            cars.remove(car.getId());
        }
        cars.add(car);*/
        driverRepository.save(car);
    }

}
