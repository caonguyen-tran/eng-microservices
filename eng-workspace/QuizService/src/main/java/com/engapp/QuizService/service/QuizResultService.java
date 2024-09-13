package com.engapp.QuizService.service;

import com.engapp.QuizService.pojo.QuizResult;

import java.util.List;

public interface QuizResultService {

    QuizResult createQuizResult(QuizResult quizResult);

    QuizResult getQuizResultByUserAndQuestionSet(String userId, int questionSetId);

    QuizResult findById(int id);

    List<QuizResult> getByOwner();
}
