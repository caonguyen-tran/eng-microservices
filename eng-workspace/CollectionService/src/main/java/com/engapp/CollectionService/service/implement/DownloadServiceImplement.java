package com.engapp.CollectionService.service.implement;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.configuration.PrincipalConfiguration;
import com.engapp.CollectionService.dto.response.CollectionResponse;
import com.engapp.CollectionService.event.DownloadEvent;
import com.engapp.CollectionService.exception.ApplicationException;
import com.engapp.CollectionService.exception.ErrorCode;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.pojo.Download;
import com.engapp.CollectionService.repository.DownloadRepository;
import com.engapp.CollectionService.service.CollectionService;
import com.engapp.CollectionService.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
        if(!getDownloadByDownloadByAndCollectionId(userDetails.getId(), collectionId)){
            Collection collection = collectionService.getCollectionById(collectionId);
            CollectionResponse collectionResponse = this.collectionMapper.collectionToCollectionResponse(collection);

            Download download = new Download();
            download.setCollection(collectionResponse);
            download.setDownloadBy(userDetails.getId());
            download.setDownloadAt(Instant.now());
            return this.downloadRepository.insert(download);
        }
        throw new ApplicationException(ErrorCode.ALREADY_EXIST);
    }

    @Override
    public List<Download> getDownloadByOwner() {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        return this.downloadRepository.findListDownloadByOwner(userDetails.getId());
    }

    @Override
    public void deleteDownload(Download download) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        if(!download.getDownloadBy().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }
        this.downloadRepository.delete(download);
    }

    @Override
    public Download getDownloadById(String id) {
        return this.downloadRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public boolean getDownloadByDownloadByAndCollectionId(String downloadBy, String collectionId) {
        if(this.downloadRepository.findByDownloadByAndCollectionId(downloadBy, collectionId) != null){
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public List<Download> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Download> pagedResult = this.downloadRepository.findAll(pageable);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<Download>();
        }
    }

    @Override
    public List<Download> getDownloadsByParams(Integer pageNo, Integer pageSize, String sortBy) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Slice<Download> sliceResult = this.downloadRepository.findDownloadByOwnerAndParams(userDetails.getId(), pageable);

        if(sliceResult.hasContent()) {
            return sliceResult.getContent();
        }
        else{
            return new ArrayList<Download>();
        }
    }

    public List<Download> getDownloadByCollectionAndUser(String collectionId, String userId) {
        return this.downloadRepository.findListDownloadByUser(collectionId, userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }
}
