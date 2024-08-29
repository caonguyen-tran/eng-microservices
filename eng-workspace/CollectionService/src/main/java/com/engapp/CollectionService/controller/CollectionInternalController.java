package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value="/internal/collection")
public class CollectionInternalController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping("/create")
    public ApiStructResponse<Collection> createCollection(@RequestBody CollectionRequest collectionRequest) {
        Collection collection = collectionService.createCollection(collectionRequest);
        return ApiStructResponse.<Collection>builder()
                .message("Create collection successfully.")
                .data(collection)
                .build();
    }
}
