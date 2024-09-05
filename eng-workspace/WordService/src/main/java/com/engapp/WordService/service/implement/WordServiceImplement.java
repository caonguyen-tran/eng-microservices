package com.engapp.WordService.service.implement;

import com.engapp.WordService.configuration.CustomUserDetails;
import com.engapp.WordService.configuration.PrincipalConfiguration;
import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.engapp.WordService.feign.CollectionClient;
import com.engapp.WordService.mapper.WordMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.repository.WordRepository;
import com.engapp.WordService.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WordServiceImplement implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private CollectionClient collectionClient;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Override
    public Word createWord(Word word) {
        if(collectionClient.inspectOwner(word.getCollectionId()).getData()){
            CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
            word.setCreatedBy(userDetails.getId());
            word.setCreatedDate(Instant.now());
            word.setUpdatedDate(Instant.now());
            return this.wordRepository.insert(word);
        }
        throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
    }

    @Override
    public List<Word> getListWordByCollectionId(String collectionId) {
        return this.wordRepository.getListWordByCollectionId(collectionId);
    }
}
