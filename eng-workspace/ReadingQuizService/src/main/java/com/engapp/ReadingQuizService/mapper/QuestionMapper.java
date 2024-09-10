package com.engapp.ReadingQuizService.mapper;

import com.engapp.ReadingQuizService.dto.request.QuestionRequest;
import com.engapp.ReadingQuizService.dto.response.QuestionResponse;
import com.engapp.ReadingQuizService.pojo.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionResponse questionToQuestionResponse(Question question);

    Question questionRequestToQuestion(QuestionRequest questionRequest);
}
