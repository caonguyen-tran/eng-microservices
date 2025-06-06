package com.engapp.WordService.service.implement;

import com.engapp.WordService.configuration.CustomUserDetails;
import com.engapp.WordService.configuration.PrincipalConfiguration;
import com.engapp.WordService.dto.request.WordLearnedRequest;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.engapp.WordService.mapper.WordMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.repository.WordLearnedRepository;
import com.engapp.WordService.service.WordLearnedService;
import com.engapp.WordService.utils.LearnedMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WordLearnedServiceImplement implements WordLearnedService {
    @Autowired
    private WordLearnedRepository wordLearnedRepository;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private WordMapper wordMapper;

    @Override
    public WordLearned createWordLearned(WordLearnedRequest wordLearnedRequest) {
        CustomUserDetails userDetails = principalConfiguration.getCustomUserDetails();
        WordLearned wordLearned = this.getWordLearnedById(wordLearnedRequest.getLearnedId());
        LearnedMaster learnedMaster = LearnedMaster.getLearnedMaster(1);
        if (wordLearned != null) {
            if (wordLearned.getLearnBy().equals(userDetails.getId())) {
                wordLearned.setLearnedMaster(learnedMaster);
                wordLearned.setReview(true);
                wordLearned.setLearn(true);
                wordLearned.setLearnDate(Instant.now());
                wordLearned.setDueDate(Instant.now().plus(learnedMaster.getDurationReminder(), ChronoUnit.HOURS));
                return this.wordLearnedRepository.save(wordLearned);
            }
        }
        return null;
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
        return this.wordLearnedRepository.findById(wordLearnedId).orElse(null);
    }

    @Override
    public void downloadListWordInCollection(List<Word> wordList, String userId, String downloadId) {
        LearnedMaster learnedMaster = LearnedMaster.getLearnedMaster(0);
        wordList.forEach(word -> CompletableFuture.runAsync(() ->
                        saveWordToWordLearned(word, learnedMaster, userId, downloadId)
                )
        );
    }

    @Override
    public List<WordLearned> filterAllByLearnedBy() {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterAllByLearnedBy(userDetails.getId());
    }

    @Override
    public List<WordLearned> filterByReview(boolean isReview) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterByReview(userDetails.getId(), isReview);
    }

    @Override
    public List<WordLearned> filterByMasterLevel(int master) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterByMasterLevel(userDetails.getId(), master);
    }

    @Override
    public List<WordLearned> filterByReviewAndLearned(boolean isReview, boolean isLearned) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterByReviewAndLearned(userDetails.getId(), isReview, isLearned);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public List<WordLearned> getAllByAdmin() {
        return this.wordLearnedRepository.findAll();
    }

    @Override
    public List<WordLearned> filterByDueDateLessThanOrEqual(Instant instant) {
        return this.wordLearnedRepository.filterByDueDateLessThanOrEqual(instant);
    }

    @Override
    public void updateReviewStatus(WordLearned wordLearned) {
        wordLearned.setReview(false);
        this.wordLearnedRepository.save(wordLearned);
    }

    @Override
    public List<WordLearned> filterByCollectionId(String collectionId) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterByCollectionIdAndLearnBy(userDetails.getId(), collectionId);
    }

    @Override
    public List<WordLearned> getTop5ByReview(boolean isReview) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        return this.wordLearnedRepository.filterTop5ByReviewAndIsReview(
                userDetails.getId(),
                isReview,
                PageRequest.of(0, 5));
    }

    @Override
    public List<WordLearned> getNonActiveInCollection(boolean isReview, boolean isLearn, String collectionId) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return this.wordLearnedRepository.filterNonActiveInCollection(userDetails.getId(), isReview, isLearn, collectionId);
    }

    @Override
    public void testService(WordLearned wordLearned) {
    }

    @Override
    public void removeLearnedInDownload(String downloadId) {
        List<WordLearned> learnedList = this.wordLearnedRepository.filterByDownloadId(downloadId);
        this.wordLearnedRepository.deleteAll(learnedList);
    }

    @Override
    public WordLearned isExistWordLearned(String learnBy, String wordId) {
        return this.wordLearnedRepository.findByUserAndWord(learnBy, wordId);
    }

    @Override
    public List<WordLearned> updateListWordLearned(List<WordLearnedRequest> wordLearnedRequests) {
        List<WordLearned> wordLearnedList = new ArrayList<>();
        for (WordLearnedRequest w : wordLearnedRequests) {
            WordLearned wordLearned = this.getWordLearnedById(w.getLearnedId());
            wordLearnedList.add(updateWordLearned(wordLearned));
        }
        return wordLearnedList;
    }

    @Override
    public List<WordLearned> createListWordLearned(List<WordLearnedRequest> wordLearnedRequests) {
        List<WordLearned> wordLearnedList = new ArrayList<>();
        for (WordLearnedRequest w : wordLearnedRequests) {
            wordLearnedList.add(createWordLearned(w));
        }
        return wordLearnedList;
    }

    public void saveWordToWordLearned(Word word, LearnedMaster learnedMaster, String userId, String downloadId) {
        WordLearned wordLearned = new WordLearned();
        wordLearned.setLearn(false);
        wordLearned.setWordResponse(this.wordMapper.wordToWordResponse(word));
        wordLearned.setSuccessRate(0.00);
        wordLearned.setReview(false);
        wordLearned.setLearnDate(Instant.now());
        wordLearned.setLearnedMaster(learnedMaster);
        wordLearned.setDueDate(Instant.now().plus(learnedMaster.getDurationReminder(), ChronoUnit.HOURS));
        wordLearned.setLearnBy(userId);
        wordLearned.setDownloadId(downloadId);
        this.wordLearnedRepository.insert(wordLearned);
    }
}
