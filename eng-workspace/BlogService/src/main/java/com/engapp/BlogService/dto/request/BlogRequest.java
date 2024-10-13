package com.engapp.BlogService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class BlogRequest {
    private String title;
    private String content;
}
