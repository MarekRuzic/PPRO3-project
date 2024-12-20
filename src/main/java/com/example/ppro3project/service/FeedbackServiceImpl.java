package com.example.ppro3project.service;

import com.example.ppro3project.model.Feedback;
import com.example.ppro3project.model.Project;
import com.example.ppro3project.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFeedbackById(long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            feedbackRepository.delete(feedback.get());
        }
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
