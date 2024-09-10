package com.engapp.ReadingQuizService.controller;

import com.engapp.ReadingQuizService.pojo.Quiz;
import com.engapp.ReadingQuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping(value="/create")
    public ResponseEntity<Quiz> postQuiz(@RequestBody Quiz quiz) {
        Quiz quizCreate = this.quizService.saveQuiz(quiz);
        return new ResponseEntity<>(quizCreate, HttpStatus.CREATED);
    }
}
