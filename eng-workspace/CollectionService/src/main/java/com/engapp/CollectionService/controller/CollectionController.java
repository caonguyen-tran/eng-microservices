package com.engapp.CollectionService.controller;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping(value="/external")
    public String index() {
        return "external request!";
    }
}
