package com.engapp.BlogService.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Setter
@Getter
public class BlogResponse {
    private Integer id;
    private String title;
    private String content;
    private Instant createdDate;
    private Instant updatedDate;
    private String userId;
}
