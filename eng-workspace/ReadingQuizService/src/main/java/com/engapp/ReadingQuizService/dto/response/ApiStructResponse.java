package com.engapp.ReadingQuizService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiStructResponse<T> {

    private int code = 2000;

    private String message;

    private T data;
}
