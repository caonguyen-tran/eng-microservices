package com.engapp.CollectionService.pojo;

import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.dto.response.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@Document(value="downloads")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Download {
    @MongoId
    String id;
    CollectionResponse collection;
    String downloadBy;
    Instant downloadAt;
}
