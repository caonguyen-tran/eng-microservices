package com.engapp.CollectionService.service.implement;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.configuration.PrincipalConfiguration;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.repository.DownloadRepository;
import com.engapp.CollectionService.service.CollectionService;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DownloadServiceImplement implements DownloadService {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private DownloadRepository downloadRepository;

    @Override
    public Download downloadCollection(String collectionId) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        Collection collection = collectionService.getCollectionById(collectionId);
        CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collection);


        Download download = new Download();
        download.setCollection(collectionResponse);
        download.setDownloadBy(userDetails.getId());
        download.setDownloadAt(Instant.now());

        return this.downloadRepository.save(download);
    }
}
