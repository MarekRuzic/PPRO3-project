package com.example.ppro3project.service;

import com.example.ppro3project.model.Registration;

import java.util.List;

public interface RegistrationService {

    List<Registration> getAllRegistrations();
    List<Registration> getAllRegistrationsForUser(String username);
    Registration getRegistrationById(long id);
    void saveRegistration(Registration registration);
    void deleteRegistrationById(long id);
}
