package com.engapp.WordService.service.implement;

import com.engapp.WordService.configuration.CustomUserDetails;
import com.engapp.WordService.configuration.PrincipalConfiguration;
import com.engapp.WordService.dto.request.WordUpdateRequest;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.engapp.WordService.feign.CollectionClient;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.repository.WordRepository;
import com.engapp.WordService.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public List<Word> getListWordByCollectionIdAndParams(String collectionId, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Slice<Word> slicedResult = this.wordRepository.getListWordByCollectionAndParams(collectionId, pageable);

        if(slicedResult.hasContent()) {
            return slicedResult.getContent();
        }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public Word updateWord(Word word, WordUpdateRequest wordUpdateRequest) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        if(word.getCreatedBy().equals(userDetails.getId())){
            word.setWord(wordUpdateRequest.getWord());
            word.setPofSpeech(wordUpdateRequest.getPofSpeech());
            word.setPronunciation(wordUpdateRequest.getPronunciation());
            word.setDefinition(wordUpdateRequest.getDefinition());
            word.setExample(wordUpdateRequest.getExample());
            word.setWordLevel(wordUpdateRequest.getWordLevel());
            word.setUpdatedDate(Instant.now());
            return this.wordRepository.save(word);
        }
        throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public String deleteWord(Word word) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        if(word.getCreatedBy().equals(userDetails.getId())){
            this.wordRepository.delete(word);
            return "Delete successfully!";
        }
        throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
    }

    @Override
    public Word getWordById(String id) {
        return this.wordRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public List<Word> getAllByAdmin() {
        return this.wordRepository.findAll();
    }

    @Override
    public List<Word> getListWordByCollectionId(String collectionId) {
        return this.wordRepository.getListWordByCollectionId(collectionId);
    }
}
