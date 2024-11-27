package com.example.ppro3project.service;

import com.example.ppro3project.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);
}
