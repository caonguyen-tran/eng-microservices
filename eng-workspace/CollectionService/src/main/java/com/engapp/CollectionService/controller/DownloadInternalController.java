package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value="/internal/download")
public class DownloadInternalController {
    @Autowired
    private DownloadService downloadService;

    @GetMapping(value="/inspect-download")
    public ApiStructResponse<Boolean> inspectDownload(@RequestParam HashMap<String, String> param) {
        String downloadBy = param.get("downloadBy");
        String collectionId = param.get("collectionId");

        boolean result = this.downloadService.getDownloadByDownloadByAndCollectionId(downloadBy, collectionId);

        return ApiStructResponse.<Boolean>builder()
                .message("Inspect user download collection.")
                .data(result)
                .build();
    }
}
