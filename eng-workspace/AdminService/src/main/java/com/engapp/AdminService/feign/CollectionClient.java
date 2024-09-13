package com.engapp.AdminService.feign;

import com.engapp.AdminService.configuration.RequestInterceptorConfiguration;
import com.engapp.AdminService.dto.clone.CollectionClone.DownloadClone;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.CollectionResponse.CollectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@FeignClient(name = "${app.admin.services.collection-service.name}", path = "${app.admin.services.collection-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface CollectionClient {
    @PutMapping(value="/admin/collection/update")
    ApiStructResponse<CollectionResponse> update(@RequestParam HashMap<String, String> params, @RequestPart("file") MultipartFile file);

    @DeleteMapping(value="/admin/collection/delete/{collectionId}")
    void delete(@PathVariable(value="collectionId") String collectionId);

    @GetMapping(value = "/admin/collection/get-all")
    ApiStructResponse<List<CollectionResponse>> getAll();

    @GetMapping(value="/admin/download/get-all")
    ApiStructResponse<List<DownloadClone>> getAllDownload(@RequestParam(defaultValue = "0") Integer pageNo
    , @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy);
}
