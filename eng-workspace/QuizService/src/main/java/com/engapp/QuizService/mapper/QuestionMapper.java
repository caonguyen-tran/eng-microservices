package com.engapp.QuizService.mapper;

import com.engapp.QuizService.dto.request.QuestionRequest;
import com.engapp.QuizService.dto.response.QuestionResponse;
import com.engapp.QuizService.pojo.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionResponse questionToQuestionResponse(Question question);

    Question questionRequestToQuestion(QuestionRequest questionRequest);
}
