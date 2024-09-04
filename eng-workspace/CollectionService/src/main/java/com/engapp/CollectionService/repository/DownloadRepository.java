package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Download;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DownloadRepository extends MongoRepository<Download, String> {

    @Query("{'collection._id': ?0, 'downloadBy': ?1}")
    public Optional<List<Download>> findListDownloadByUser(String collectionId, String userId);

    @Query("{downloadBy: ?0}")
    public Optional<List<Download>> findListDownloadByOwner(String userId);
}
