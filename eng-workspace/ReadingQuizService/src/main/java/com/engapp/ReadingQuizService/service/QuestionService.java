package com.engapp.ReadingQuizService.service;

import com.engapp.ReadingQuizService.dto.request.QuestionRequest;
import com.engapp.ReadingQuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.ReadingQuizService.pojo.Question;

import java.util.List;

public interface QuestionService {

    Question createQuestion(QuestionRequest questionRequest);

    List<Question> getAllQuestions();

    List<Question> getByQuestionSetId(String questionSetId);

    Question updateQuestion(QuestionUpdateRequest questionUpdateRequest);

    void deleteQuestion(String questionId);

    Question getQuestionByQuestionNumber(int questionNumber);

    Question getQuestionById(String questionId);

    void saveQuestion(Question question);
}
