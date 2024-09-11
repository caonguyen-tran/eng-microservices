package com.engapp.ReadingQuizService.service;

import com.engapp.ReadingQuizService.dto.request.AnswerRequest;
import com.engapp.ReadingQuizService.dto.request.update.AnswerUpdateRequest;
import com.engapp.ReadingQuizService.pojo.Answer;

import java.util.List;

public interface AnswerService {

    Answer createAnswer(AnswerRequest answerRequest);

    List<Answer> createMultipleAnswers(List<AnswerRequest> answerRequests);

    Answer updateAnswer(AnswerUpdateRequest answerUpdateRequest);

    Answer getAnswerById(int id);

    List<Answer> updateMultipleAnswers(List<AnswerUpdateRequest> answerUpdateRequests);

    List<Answer> getAnswersByQuestionId(int questionId);
}
