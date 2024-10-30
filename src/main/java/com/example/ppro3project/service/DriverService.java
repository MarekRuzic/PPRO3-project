package com.example.ppro3project.service;

import com.example.ppro3project.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> getAllDrivers();
    Driver getDriverById(long id);
    void deleteDriverById(long id);
    void saveDriver(Driver car);
}
