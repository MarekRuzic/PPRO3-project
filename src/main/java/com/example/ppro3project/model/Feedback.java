package com.example.ppro3project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User feedbackUser;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project feedbackProject;

    @Min(value = 1, message = "Hodnota je příliž malá")
    @Max(value = 5, message = "Hodnota je příliž velká")
    private int rating;

    @NotBlank
    private String comment;

    private LocalDateTime feedbackDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(User user) {
        this.feedbackUser = user;
    }

    public Project getFeedbackProject() {
        return feedbackProject;
    }

    public void setFeedbackProject(Project project) {
        this.feedbackProject = project;
    }

    @Min(value = 1, message = "Hodnota je příliž malá")
    @Max(value = 5, message = "Hodnota je příliž velká")
    public int getRating() {
        return rating;
    }

    public void setRating(@Min(value = 1, message = "Hodnota je příliž malá") @Max(value = 5, message = "Hodnota je příliž velká") int rating) {
        this.rating = rating;
    }

    public @NotBlank(message = "Je potřeba napsat datum") String getComment() {
        return comment;
    }

    public void setComment(@NotBlank(message = "Je potřeba napsat datum") String comment) {
        this.comment = comment;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public String getFeedbackDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return feedbackDate.format(formatter);
    }

    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
