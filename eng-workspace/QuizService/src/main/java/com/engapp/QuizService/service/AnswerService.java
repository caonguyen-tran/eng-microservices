package com.engapp.QuizService.service;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.dto.request.update.AnswerUpdateRequest;
import com.engapp.QuizService.pojo.Answer;

import java.util.List;
import java.util.Set;

public interface AnswerService {

    Answer createAnswer(AnswerRequest answerRequest);

    Set<Answer> createMultipleAnswers(Set<AnswerRequest> answerRequests);

    Answer updateAnswer(AnswerUpdateRequest answerUpdateRequest);

    Answer getAnswerById(int id);

    Set<Answer> updateMultipleAnswers(Set<AnswerUpdateRequest> answerUpdateRequests);

    List<Answer> getAnswersByQuestionId(int questionId);
}
