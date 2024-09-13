package com.engapp.CollectionService.controller.admin;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import com.engapp.CollectionService.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/collection")
public class AdminCollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private ImageUploadService imageUploadService;

    @PutMapping(value="/update")
    public ApiStructResponse<CollectionResponse> update(@RequestParam HashMap<String, String> params, @RequestPart(value="file") MultipartFile file) {
        String id = params.get("id");
        String name = params.get("name");
        String description = params.get("description");

        Map res = this.imageUploadService.uploadImage(file, "collection-service");
        Collection collection = this.collectionService.getCollectionById(id);
        collection.setName(name);
        collection.setDescription(description);
        collection.setImage(res.get("secure_url").toString());

        Collection collectionUpdate = collectionService.updateCollectionByAdmin(collection);
        CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collectionUpdate);

        return ApiStructResponse.<CollectionResponse>builder()
                .message("Update Collection successfully.")
                .data(collectionResponse)
                .build();
    }

    @DeleteMapping(value="/delete/{collectionId}")
    public void delete(@PathVariable(value="collectionId") String collectionId) {
        this.collectionService.deleteCollectionByAdmin(collectionId);
    }

    @GetMapping(value = "/get-all")
    public ApiStructResponse<List<CollectionResponse>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy) {
        List<Collection> collections = this.collectionService.getCollectionsByParams(pageNo, pageSize, sortBy);

        List<CollectionResponse> collectionResponseList = collections
                .stream()
                .map(item -> this.collectionMapper.collectionToCollectionResponse(item))
                .toList();

        return ApiStructResponse.<List<CollectionResponse>>builder()
                .message("Get all Collections successfully.")
                .data(collectionResponseList)
                .build();
    }
}
