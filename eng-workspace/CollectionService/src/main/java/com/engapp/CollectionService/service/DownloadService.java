package com.engapp.CollectionService.service;

import com.engapp.CollectionService.pojo.Download;

import java.util.List;

public interface DownloadService {

    public Download downloadCollection(String collectionId);

    public List<Download> getDownloadByOwner();

    public void deleteDownload(Download download);

    public Download getDownloadById(String id);
}
