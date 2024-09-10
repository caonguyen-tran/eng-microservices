package com.engapp.ReadingQuizService.mapper;

import com.engapp.ReadingQuizService.dto.request.AnswerRequest;
import com.engapp.ReadingQuizService.pojo.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer answerRequestToAnswer(AnswerRequest answerRequest);
}
