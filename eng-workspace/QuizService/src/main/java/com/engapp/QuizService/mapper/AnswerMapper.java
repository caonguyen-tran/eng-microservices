package com.engapp.QuizService.mapper;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.pojo.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer answerRequestToAnswer(AnswerRequest answerRequest);
}
