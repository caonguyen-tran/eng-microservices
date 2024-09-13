package com.engapp.QuizService.mapper;

import com.engapp.QuizService.dto.response.ExamResponsesExerciseResponse;
import com.engapp.QuizService.dto.response.ExamResponsesResultResponse;
import com.engapp.QuizService.pojo.ExamResponses;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamResponsesMapper {
    ExamResponsesExerciseResponse examResponsesToExamResponsesExerciseResponse(ExamResponses examResponses);

    ExamResponsesResultResponse examResponsesToExamResponsesResultResponse(ExamResponses examResponses);
}
