package com.engapp.CollectionService.controller.admin;

import com.engapp.CollectionService.dto.response.ApiStructResponse;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/download")
public class AdminDownloadController {
    @Autowired
    private DownloadService downloadService;

    @GetMapping(value = "/get-all")
    public ApiStructResponse<List<Download>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        List<Download> downloads = downloadService.getAll(pageNo, pageSize, sortBy);

        return ApiStructResponse.<List<Download>>builder()
                .message("Get list downloads.")
                .data(downloads)
                .build();
    }
}
