package com.engapp.WordService.dto.request;


import com.engapp.WordService.utils.PofSpeech;
import com.engapp.WordService.utils.WordLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class WordRequest {
    private String word;
    private PofSpeech pofSpeech;
    private String pronunciation;
    private String definition;
    private String example;
    private WordLevel wordLevel;
    private String collectionId;
}
