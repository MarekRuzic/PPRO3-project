package com.example.ppro3project;

import com.example.ppro3project.model.User;
import com.example.ppro3project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ppro3ProjectApplication {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public Ppro3ProjectApplication(UserService userService, PasswordEncoder passwordEncoder){
        this.userService =userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            addUser("admin", "heslo", "ORGANIZATOR", "admin@admin.cz");
            addUser("user", "heslo", "DOBROVOLNIK", "user@user.cz");
        };
    }

    private void addUser(String username, String password, String role, String email) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            user.setEmail(email);
            userService.save(user);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Ppro3ProjectApplication.class, args);
    }

}
