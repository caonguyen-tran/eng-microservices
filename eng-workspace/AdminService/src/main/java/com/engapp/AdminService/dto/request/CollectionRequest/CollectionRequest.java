package com.engapp.AdminService.dto.request.CollectionRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class CollectionRequest {
    private String id;
    private String name;
    private String description;
    private MultipartFile file;
}
