package com.engapp.WordService.mapper;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.pojo.Word;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper {

    public Word wordRequestToWord(WordRequest wordRequest);

    public WordResponse wordToWordResponse(Word word);
}
