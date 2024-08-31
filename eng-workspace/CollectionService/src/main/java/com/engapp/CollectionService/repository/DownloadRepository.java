package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Download;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadRepository extends MongoRepository<Download, String> {
}
