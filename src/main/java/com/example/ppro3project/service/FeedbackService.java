package com.example.ppro3project.service;

import com.example.ppro3project.model.Feedback;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(long id);
    void deleteFeedbackById(long id);
    void saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacksForProject(long projectId);
}
