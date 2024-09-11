package com.engapp.QuizService.mapper;

import com.engapp.QuizService.dto.request.QuestionSetRequest;
import com.engapp.QuizService.dto.response.QuestionSetResponse;
import com.engapp.QuizService.pojo.QuestionSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionSetMapper {

    QuestionSetResponse questionSetToQuestionSetResponse(QuestionSet questionSet);

    QuestionSet questionSetRequestToQuestionSet(QuestionSetRequest questionSetRequest);
}
