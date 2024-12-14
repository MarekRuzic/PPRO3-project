package com.example.ppro3project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @NotBlank
    private String description;

    // Místo konání projektu
    @NotBlank
    private String location;

    // Datum konání
    @NotNull(message = "Neplatné datum")
    private LocalDateTime date;

    // Uživatel, který koná akci
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Project(long id, String name, String description, String location, LocalDateTime date, User organizer) {
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

    public @NotNull LocalDateTime getDate() {
        return date;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return date.format(formatter);
    }

    public void setDate(@NotNull LocalDateTime date) {
        if (date.isBefore(LocalDateTime.now()))
        {
            return;
        }
        this.date = date;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
