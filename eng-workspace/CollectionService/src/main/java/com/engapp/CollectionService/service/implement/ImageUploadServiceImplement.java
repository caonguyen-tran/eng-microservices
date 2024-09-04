package com.engapp.CollectionService.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.engapp.CollectionService.exception.ApplicationException;
import com.engapp.CollectionService.exception.ErrorCode;
import com.engapp.CollectionService.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageUploadServiceImplement implements ImageUploadService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map uploadImage(MultipartFile file, String folderName) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folderName,
                            "resource_type", "auto"
                    ));
            return uploadResult;
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.RUNTIME_EXCEPTION);
        }
    }
}
