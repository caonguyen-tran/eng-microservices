package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(value="/remove/{downloadId}")
    public ApiStructResponse<String> delete(@PathVariable("downloadId") String downloadId){
        Download download = this.downloadService.getDownloadById(downloadId);
        this.downloadService.deleteDownload(download);
        return ApiStructResponse.<String>builder()
                .message("List downloaded of user.")
                .data("Delete download from owner.")
                .build();
    }

    @GetMapping(value="/get/list-downloaded")
    public ApiStructResponse<List<Download>> getListDownloaded(){
        List<Download> downloads = this.downloadService.getDownloadByOwner();

        return ApiStructResponse.<List<Download>>builder()
                .message("List downloaded of user.")
                .data(downloads)
                .build();
    }

    @DeleteMapping(value="/admin/remove/{downloadId}")
    public String adminRemove(@PathVariable("downloadId") String downloadId){
        return null;
    }
}
