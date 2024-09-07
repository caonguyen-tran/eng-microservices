package com.engapp.WordService.service.implement;

import com.engapp.WordService.configuration.CustomUserDetails;
import com.engapp.WordService.configuration.PrincipalConfiguration;
import com.engapp.WordService.dto.request.WordLearnedCreateRequest;
import com.engapp.WordService.dto.request.WordLearnedUpdateRequest;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.engapp.WordService.feign.CollectionClient;
import com.engapp.WordService.mapper.WordMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.repository.WordLearnedRepository;
import com.engapp.WordService.service.WordLearnedService;
import com.engapp.WordService.service.WordService;
import com.engapp.WordService.utils.LearnedMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class WordLearnedServiceImplement implements WordLearnedService {
    @Autowired
    private WordLearnedRepository wordLearnedRepository;

    @Autowired
    private WordService wordService;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private CollectionClient collectionClient;

    //Có thời gian sẽ tái cấu trúc source, cái ni là phá bỏ tiêu chuẩn
    @Autowired
    private WordMapper wordMapper;

    @Override
    public WordLearned createWordLearned(WordLearnedCreateRequest wordLearnedCreateRequest) {
        CustomUserDetails customUserDetails = principalConfiguration.getCustomUserDetails();

        Word word = this.wordService.getWordById(wordLearnedCreateRequest.getWordId());

        HashMap<String, String> param = new HashMap<>();
        param.put("downloadBy", customUserDetails.getId());
        param.put("collectionId", word.getCollectionId());

        if (this.collectionClient.inspectDownload(param).getData()) {
            if (isExistWordLearned(customUserDetails.getId(), wordLearnedCreateRequest.getWordId()) == null) {
                LearnedMaster learnedMaster = LearnedMaster.getLearnedMaster(1);
                WordLearned wordLearned = new WordLearned();
                wordLearned.setWordResponse(this.wordMapper.wordToWordResponse(word));
                wordLearned.setLearnedMaster(learnedMaster);
                wordLearned.setSuccessRate(0.00);
                wordLearned.setReview(true);
                wordLearned.setLearnDate(Instant.now());
                wordLearned.setDueDate(Instant.now().plus(learnedMaster.getDurationReminder(), ChronoUnit.HOURS));
                wordLearned.setLearnBy(customUserDetails.getId());
                return this.wordLearnedRepository.insert(wordLearned);
            }
            throw new ApplicationException(ErrorCode.ALREADY_EXIST);
        }

        throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
    }

    @Override
    public WordLearned updateWordLearned(WordLearned wordLearned) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        LearnedMaster learnedMaster = wordLearned.getLearnedMaster();

        if (userDetails.getId().equals(wordLearned.getLearnBy())) {
            if (learnedMaster.getKey() == 6) {
                wordLearned.setReview(true);
                wordLearned.setDueDate(Instant.now().plus(learnedMaster.getDurationReminder(), ChronoUnit.HOURS));
                return this.wordLearnedRepository.save(wordLearned);
            } else {
                LearnedMaster learnedMasterUpdate = LearnedMaster.getLearnedMaster(learnedMaster.getKey() + 1);
                wordLearned.setLearnedMaster(learnedMasterUpdate);
                wordLearned.setReview(true);
                wordLearned.setDueDate(Instant.now().plus(learnedMasterUpdate.getDurationReminder(), ChronoUnit.HOURS));
                return this.wordLearnedRepository.save(wordLearned);
            }
        }
        throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
    }

    @Override
    public WordLearned getWordLearnedById(String wordLearnedId) {
        return this.wordLearnedRepository.findById(wordLearnedId).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public WordLearned isExistWordLearned(String learnBy, String wordId) {
        return this.wordLearnedRepository.findByUserAndWord(learnBy, wordId);
    }

    @Override
    public List<WordLearned> updateListWordLearned(List<WordLearnedUpdateRequest> wordLearnedUpdateRequests) {
        List<WordLearned> wordLearnedList = new ArrayList<>();
        for (WordLearnedUpdateRequest w : wordLearnedUpdateRequests) {
            WordLearned wordLearned = this.getWordLearnedById(w.getLearnedId());
            wordLearnedList.add(updateWordLearned(wordLearned));
        }
        return wordLearnedList;
    }

    @Override
    public List<WordLearned> createListWordLearned(List<WordLearnedCreateRequest> wordLearnedCreateRequestList) {
        List<WordLearned> wordLearnedList = new ArrayList<>();
        for (WordLearnedCreateRequest w : wordLearnedCreateRequestList) {
            wordLearnedList.add(createWordLearned(w));
        }
        return wordLearnedList;
    }
}
