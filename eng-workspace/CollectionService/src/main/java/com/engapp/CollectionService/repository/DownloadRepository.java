package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Download;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DownloadRepository extends MongoRepository<Download, String> {

    @Query("{'collection._id': ?0, 'downloadBy': ?1}")
    Optional<List<Download>> findListDownloadByUser(String collectionId, String userId);

    @Query("{downloadBy: ?0}")
    List<Download> findListDownloadByOwner(String userId);

    @Query("{'downloadBy': ?0, 'collection._id':  ?1}")
    Download findByDownloadByAndCollectionId(String downloadBy, String collectionId);

    @Query("{'downloadBy': ?0}")
    Slice<Download> findDownloadByOwnerAndParams(String userId, Pageable pageable);
}
