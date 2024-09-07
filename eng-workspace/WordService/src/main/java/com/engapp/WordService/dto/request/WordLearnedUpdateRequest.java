package com.engapp.WordService.dto.request;

import com.engapp.WordService.utils.LearnedMaster;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class WordLearnedUpdateRequest {
    private String learnedId;
}
