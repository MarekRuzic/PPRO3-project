package com.example.ppro3project.service;

import com.example.ppro3project.model.Project;
import com.example.ppro3project.model.Registration;
import com.example.ppro3project.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public List<Registration> getAllRegistrationsForUser(String username) {
        registrationRepository.findAll();
        List<Registration> registrations = new ArrayList<>();
        for (Registration registration : registrationRepository.findAll()) {
            if (registration.getUser().getUsername().equals(username)) {
                registrations.add(registration);
            }
        }
        return registrations;
    }

    @Override
    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    @Override
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistrationById(long id) {
        Optional<Registration> registration = registrationRepository.findById(id);
        if (registration.isPresent()) {
            registrationRepository.delete(registration.get());
        }
    }
}
