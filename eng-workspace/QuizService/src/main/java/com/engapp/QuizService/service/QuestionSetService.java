package com.engapp.QuizService.service;

import com.engapp.QuizService.dto.request.update.QuestionSetUpdateRequest;
import com.engapp.QuizService.pojo.QuestionSet;

import java.util.List;

public interface QuestionSetService {

    QuestionSet createQuestionSet(QuestionSet questionSet);

    List<QuestionSet> getAllQuestionSets();

    List<QuestionSet> getQuestionSetByParams(Integer pageNo, Integer pageSize, String sortBy);

    QuestionSet getQuestionSetById(int id);

    List<QuestionSet> getQuestionSetByReadingPartAndYearOf(int readingPart, int yearOf);

    List<QuestionSet> getQuestionSetByReadingPart(int readingPart);

    QuestionSet updateQuestionSet(QuestionSetUpdateRequest questionSetUpdateRequest);

    List<QuestionSet> getQuestionSetByName(String name);

    void deleteQuestionSetById(int id);
}
