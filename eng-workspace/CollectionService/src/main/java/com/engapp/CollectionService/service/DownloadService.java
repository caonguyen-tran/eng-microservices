package com.engapp.CollectionService.service;

import com.engapp.CollectionService.pojo.Download;

import java.util.List;

public interface DownloadService {

    Download downloadCollection(String collectionId);

    List<Download> getDownloadByOwner();

    void deleteDownload(Download download);

    Download getDownloadById(String id);

    boolean getDownloadByDownloadByAndCollectionId(String downloadBy, String collectionId);

    List<Download> getAll(Integer pageNo, Integer pageSize, String sortBy);

    List<Download> getDownloadsByParams(Integer pageNo, Integer pageSize, String sortBy);
}
