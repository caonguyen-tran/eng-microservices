package com.engapp.ReadingQuizService.service;

import com.engapp.ReadingQuizService.dto.request.QuestionRequest;
import com.engapp.ReadingQuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.ReadingQuizService.pojo.Question;

import java.util.List;

public interface QuestionService {

    Question createQuestion(QuestionRequest questionRequest);

    List<Question> getAllQuestions();

    List<Question> getByQuestionSetId(int questionSetId);

    Question updateQuestion(QuestionUpdateRequest questionUpdateRequest);

    void deleteQuestion(int questionId);

    Question getQuestionByQuestionNumber(int questionId, int questionNumber);

    Question getQuestionById(int questionId);

    void saveQuestion(Question question);
}
