package com.engapp.QuizService.service;

import com.engapp.QuizService.pojo.ExamResponses;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuizResult;

import java.util.List;

public interface ExamResponsesService {
    List<ExamResponses> createMultipleExamResponses(List<Question> questions, QuizResult quizResult);

    ExamResponses createSingleExamResponse(Question question, QuizResult quizResult, String userId);

    List<ExamResponses> getMultipleExamResponses(QuizResult quizResult);
}
