package com.example.ppro3project.service;

import com.example.ppro3project.model.Driver;

import java.util.ArrayList;

public interface DriverService {

    ArrayList<Driver> getAllDrivers();
    Driver getDriverById(int id);
    void deleteDriverById(int id);
    void saveDriver(Driver car);
}
