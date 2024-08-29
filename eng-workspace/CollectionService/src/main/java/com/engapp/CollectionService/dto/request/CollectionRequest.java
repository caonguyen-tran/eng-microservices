package com.engapp.CollectionService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CollectionRequest {
    private String name;
    private String description;
}
