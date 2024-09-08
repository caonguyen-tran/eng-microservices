package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import com.engapp.CollectionService.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping(value = "/external")
    public String index() {
        return "external request!";
    }

    @PostMapping( value="/create")
    public ApiStructResponse<CollectionResponse> createCollection(@RequestParam HashMap<String, String> data, @RequestPart MultipartFile file) {
        String name = data.get("name");
        String description = data.get("description");

        Map res = this.imageUploadService.uploadImage(file, "collection-service");
        Collection collection = new Collection();
        collection.setName(name);
        collection.setDescription(description);
        collection.setImage(res.get("secure_url").toString());

        Collection collectionCreate = collectionService.createCollection(collection);
        CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collectionCreate);

        return ApiStructResponse.<CollectionResponse>builder()
                .message("Create collection successfully.")
                .data(collectionResponse)
                .build();
    }

    @GetMapping(value = "/{collectionId}")
    public ApiStructResponse<CollectionResponse> get(@PathVariable("collectionId") String collectionId) {
        Collection collection = this.collectionService.getCollectionById(collectionId);
        CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collection);

        return ApiStructResponse.<CollectionResponse>builder()
                .message("Collection results.")
                .data(collectionResponse)
                .build();
    }

    @DeleteMapping(value = "/delete/{collectionId}")
    public ApiStructResponse<String> delete(@PathVariable("collectionId") String collectionId) {
        Collection collection = this.collectionService.getCollectionById(collectionId);
        this.collectionService.deleteCollectionById(collection);
        return ApiStructResponse.<String>builder()
                .message("Delete collection request")
                .data("Delete collection successfully.")
                .build();
    }

    @PatchMapping(value = "/update/{collectionId}")
    public ApiStructResponse<CollectionResponse> update(@PathVariable("collectionId") String collectionId, @RequestBody CollectionRequest collectionRequest) {
        Collection collection = this.collectionService.getCollectionById(collectionId);
        Collection collectionUpdate = this.collectionService.updateCollection(collectionRequest, collection);
        CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collectionUpdate);
        return ApiStructResponse.<CollectionResponse>builder()
                .message("Patch collection request")
                .data(collectionResponse)
                .build();
    }

    @GetMapping(value = "/list/my-collection")
    public ApiStructResponse<List<CollectionResponse>> getMyCollection() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        List<Collection> collections = this.collectionService.getCollectionByCreatedBy(userDetails.getId());
        List<CollectionResponse> collectionResponses = collections
                .stream()
                .map((collection -> this.collectionMapper.collectionToCollectionResponse(collection)))
                .toList();

        return ApiStructResponse.<List<CollectionResponse>>builder()
                .message("Get owner collections request.")
                .data(collectionResponses)
                .build();
    }

    @GetMapping(value="/list-by-user")
    public ApiStructResponse<List<CollectionResponse>> getCollectionsByUser(@RequestParam("userId") String userId) {
        List<Collection> collections = this.collectionService.getCollectionByCreatedBy(userId);
        List<CollectionResponse> collectionResponses = collections
                .stream()
                .map((collection -> this.collectionMapper.collectionToCollectionResponse(collection)))
                .toList();

        return ApiStructResponse.<List<CollectionResponse>>builder()
                .message("Get collections by userId.")
                .data(collectionResponses)
                .build();
    }

    @GetMapping(value="/all")
    public ApiStructResponse<List<CollectionResponse>> getAllCollections() {
        List<Collection> collections = this.collectionService.getAllCollections();
        List<CollectionResponse> collectionResponses = collections
                .stream()
                .map((collection -> this.collectionMapper.collectionToCollectionResponse(collection)))
                .toList();

        return ApiStructResponse.<List<CollectionResponse>>builder()
                .message("Get all collections.")
                .data(collectionResponses)
                .build();
    }
}
