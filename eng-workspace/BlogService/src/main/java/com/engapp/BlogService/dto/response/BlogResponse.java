package com.engapp.BlogService.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BlogResponse {
    private String title;
    private String content;
}
