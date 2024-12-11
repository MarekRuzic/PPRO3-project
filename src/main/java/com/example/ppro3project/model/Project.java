package com.example.ppro3project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {

    // Unikátní identifikátor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Název projektu
    @NotBlank
    private String name;

    // Popisek projektu
    private String description;

    // Místo konání projektu
    @NotBlank
    private String location;

    // Datum konání
    @NotBlank
    private LocalDate date;

    // Uživatel, který koná akci
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Project(long id, String name, String description, String location, LocalDate date, User organizer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.organizer = organizer;
    }

    public Project() {

    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public @NotBlank LocalDate getDate() {
        return date;
    }

    public void setDate(@NotBlank LocalDate date) {
        this.date = date;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
