package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.configuration.PrincipalConfiguration;
import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.event.DownloadEvent;
import com.engapp.CollectionService.event.Producer.Producer;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.service.DownloadService;
import com.engapp.CollectionService.utils.TopicEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value="/download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @Autowired
    private Producer<DownloadEvent> producer;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @PostMapping(value="/create")
    public ApiStructResponse<Download> create(@RequestParam("collectionId") String collectionId){
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        Download download = this.downloadService.downloadCollection(collectionId);

        if(download != null){
            DownloadEvent downloadEvent = new DownloadEvent(collectionId, userDetails.getId());

            producer.sendMessage(TopicEnum.COLLECTION_DOWNLOAD.getTopic(), downloadEvent);
        }
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
    public ApiStructResponse<List<Download>> getListDownloaded(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy){
        List<Download> downloads = this.downloadService.getDownloadsByParams(pageNo, pageSize, sortBy);

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
