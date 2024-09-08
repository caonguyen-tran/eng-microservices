package com.engapp.CollectionService.controller.admin;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/collection")
public class AdminCollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionMapper collectionMapper;

    @PatchMapping(value="/update")
    public ApiStructResponse<CollectionResponse> update(@RequestBody CollectionRequest collectionRequest) {
        Collection collection = this.collectionService.getCollectionById(collectionRequest.getId());
        Collection collectionUpdate = this.collectionService.updateCollectionByAdmin(collection, collectionRequest);
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
}
