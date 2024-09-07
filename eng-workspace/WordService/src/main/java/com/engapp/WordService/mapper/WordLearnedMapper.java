package com.engapp.WordService.mapper;

import com.engapp.WordService.dto.response.WordLearnedResponse;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.pojo.WordLearned;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordLearnedMapper {

    public WordLearnedResponse wordLearnedToWordLearnedResponse(WordLearned wordLearned);
}
