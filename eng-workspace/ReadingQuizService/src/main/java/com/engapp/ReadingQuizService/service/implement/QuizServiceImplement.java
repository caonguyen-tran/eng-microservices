package com.engapp.ReadingQuizService.service.implement;

import com.engapp.ReadingQuizService.pojo.Quiz;
import com.engapp.ReadingQuizService.repository.QuizRepository;
import com.engapp.ReadingQuizService.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuizServiceImplement implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
