package com.engapp.ReadingQuizService.mapper;

import com.engapp.ReadingQuizService.dto.request.QuestionSetRequest;
import com.engapp.ReadingQuizService.dto.response.QuestionSetResponse;
import com.engapp.ReadingQuizService.pojo.QuestionSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionSetMapper {

    QuestionSetResponse questionSetToQuestionSetResponse(QuestionSet questionSet);

    QuestionSet questionSetRequestToQuestionSet(QuestionSetRequest questionSetRequest);
}
