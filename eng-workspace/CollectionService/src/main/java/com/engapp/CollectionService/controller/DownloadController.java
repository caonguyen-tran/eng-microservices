package com.engapp.CollectionService.controller;

import com.cloudinary.Api;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @PostMapping(value="/create")
    public ApiStructResponse<Download> create(@RequestParam("collectionId") String collectionId){
        Download download = this.downloadService.downloadCollection(collectionId);
        return ApiStructResponse.<Download>builder()
                .message("Download collection successfully.")
                .data(download)
                .build();
    }
}
