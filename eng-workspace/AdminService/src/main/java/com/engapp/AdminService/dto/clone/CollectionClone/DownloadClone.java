package com.engapp.AdminService.dto.clone.CollectionClone;

import com.engapp.AdminService.dto.response.CollectionResponse.CollectionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DownloadClone {
    String id;
    CollectionResponse collection;
    String downloadBy;
    Instant downloadAt;
}
