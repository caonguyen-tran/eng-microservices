package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.dto.request.OwnerRequest;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/internal/collection")
public class CollectionInternalController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping(value="/inspect-owner")
    public ApiStructResponse<Boolean> inspectOwner(@RequestParam(value="collectionId") String collectionId) {
        Collection collection = this.collectionService.getCollectionById(collectionId);
        boolean inspectResult = this.collectionService.inspectCollectionOwner(collection);
        return ApiStructResponse.<Boolean>builder()
                .message("Get inspect owner result.")
                .data(inspectResult)
                .build();
    }
}
