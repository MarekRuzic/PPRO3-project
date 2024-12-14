package com.example.ppro3project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "registration")
public class Registration {

    // Unikátní identifikátor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotNull(message = "Neplatné datum")
    private LocalDate date;

    public Registration() {

    }

    public Registration(long id, User user, Project project, LocalDate date) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "Neplatné datum") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Neplatné datum") LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
