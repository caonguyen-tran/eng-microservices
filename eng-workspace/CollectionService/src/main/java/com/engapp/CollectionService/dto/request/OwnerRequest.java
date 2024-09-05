package com.engapp.CollectionService.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
    private String ownerId;
    private String collectionId;
}
