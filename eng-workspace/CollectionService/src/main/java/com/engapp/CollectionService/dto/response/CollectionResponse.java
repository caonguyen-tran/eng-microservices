package com.engapp.CollectionService.dto.response;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
    private String id;
    private String name;
    private String userId;
}
