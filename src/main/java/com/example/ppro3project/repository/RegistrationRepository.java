package com.example.ppro3project.repository;

import com.example.ppro3project.model.Project;
import com.example.ppro3project.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
