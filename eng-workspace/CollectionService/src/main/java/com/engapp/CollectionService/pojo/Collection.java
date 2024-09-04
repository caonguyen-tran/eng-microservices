package com.engapp.CollectionService.pojo;

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
@Document(value="collection")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    @MongoId
    String id;
    String name;
    String description;
    String image;
    Instant createAt;
    Instant updateAt;
    String createBy;
    List<UserResponse> downloadBy;
}
