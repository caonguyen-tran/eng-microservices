package com.engapp.AdminService.controller;

import com.engapp.AdminService.dto.clone.CollectionClone.DownloadClone;
import com.engapp.AdminService.dto.request.CollectionRequest.CollectionRequest;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.CollectionResponse.CollectionResponse;
import com.engapp.AdminService.feign.CollectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/collection")
public class AdminCollectionController {
    @Autowired
    private CollectionClient collectionClient;

    @PutMapping(value="/update")
    ApiStructResponse<CollectionResponse> update(@RequestParam HashMap<String, String> params, @RequestPart("file") MultipartFile file){
        return collectionClient.update(params, file);
    }

    @DeleteMapping(value="/delete/{collectionId}")
    void delete(@PathVariable(value="collectionId") String collectionId){
        collectionClient.delete(collectionId);
    }

    @GetMapping(value = "/get-all")
    ApiStructResponse<List<CollectionResponse>> getAll(){
        return collectionClient.getAll();
    }

    @GetMapping(value="/get-all-download")
    ApiStructResponse<List<DownloadClone>> getAllDownload(@RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return collectionClient.getAllDownload(pageNo, pageSize, sortBy);
    }
}
