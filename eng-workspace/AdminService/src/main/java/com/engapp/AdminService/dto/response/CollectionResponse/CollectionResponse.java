package com.engapp.AdminService.dto.response.CollectionResponse;

import lombok.*;

import java.time.Instant;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
    private String id;
    private String name;
    private String description;
    private String image;
    private Instant createAt;
    private Instant updateAt;
    private String createBy;
}
