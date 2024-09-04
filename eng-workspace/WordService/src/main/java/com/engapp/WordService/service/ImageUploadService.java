package com.engapp.WordService.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageUploadService {

    public Map uploadImage(MultipartFile file, String folderName);
}
