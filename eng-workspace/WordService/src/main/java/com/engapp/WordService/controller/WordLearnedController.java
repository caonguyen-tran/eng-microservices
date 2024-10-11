package com.engapp.WordService.controller;

import com.engapp.WordService.dto.request.WordLearnedRequest;
import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordLearnedResponse;
import com.engapp.WordService.event.DownloadEvent;
import com.engapp.WordService.event.WordLearnedEvent;
import com.engapp.WordService.mapper.WordLearnedMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.service.WordLearnedService;
import com.engapp.WordService.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/learned-word")
@Slf4j
public class WordLearnedController {
    @Autowired
    private WordLearnedService wordLearnedService;

    @Autowired
    private WordLearnedMapper wordLearnedMapper;

    @Autowired
    private WordService wordService;

    @PostMapping(value = "/learn-first-time")
    public ApiStructResponse<List<WordLearnedResponse>> createWordLearned(@RequestBody List<WordLearnedRequest> wordLearnedRequests) {
        List<WordLearned> listWordLearned = this.wordLearnedService.createListWordLearned(wordLearnedRequests);

        List<WordLearnedResponse> listWordLearnedResponse = listWordLearned
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Create word learned.")
                .data(listWordLearnedResponse)
                .build();
    }

    @PatchMapping(value = "/update-list-word")
    public ApiStructResponse<List<WordLearnedResponse>> updateListWord(@RequestBody List<WordLearnedRequest> listWordLearnedRequest) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.updateListWordLearned(listWordLearnedRequest);
        List<WordLearnedResponse> listWordLearnedResponse = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Update word list.")
                .data(listWordLearnedResponse)
                .build();
    }

    @KafkaListener(topics = "collection-download", groupId = "handle-event-group", containerFactory = "kafkaListenerDownloadEventContainerFactory")
    public void listenCollectionDownloadEvent(DownloadEvent downloadEvent) {
        List<Word> listWords = this.wordService.getListWordByCollectionId(downloadEvent.getCollectionId());
        this.wordLearnedService.downloadListWordInCollection(listWords, downloadEvent.getUserId(), downloadEvent.getDownloadId());
    }


    @KafkaListener(topics = "delete-download", groupId = "handle-event-group", containerFactory = "kafkaListenerWordLearnedDeleteContainerFactory")
    public void listenDeleteDownloadEvent(String downloadId) {
        log.info("Delete download event: {}", downloadId);
        wordLearnedService.removeLearnedInDownload(downloadId);
    }

    @GetMapping(value = "/get-lte-due-date")
    public ApiStructResponse<List<WordLearnedResponse>> getByDueDateLte() {
        List<WordLearned> wordLearnedList = this.wordLearnedService.filterByDueDateLessThanOrEqual(Instant.now());
        List<WordLearnedResponse> wordLearnedResponseList = wordLearnedList.stream().map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item)).toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get list word learned by due date less than or equal NOW().")
                .data(wordLearnedResponseList)
                .build();
    }

    @KafkaListener(topics = "update-review", groupId = "handle-event-group", containerFactory = "kafkaListenerWordLearnedContainerFactory")
    public void listenUpdateViewEvent(WordLearnedEvent wordLearnedEvent) {
        WordLearned wordLearned = this.wordLearnedService.getWordLearnedById(wordLearnedEvent.getId());
        if (wordLearned.isReview()) {
            if (wordLearned.getDueDate().isBefore(Instant.now())) {
                this.wordLearnedService.updateReviewStatus(wordLearned);
            }
        }
    }

    @GetMapping(value = "/get-by-review")
    public ApiStructResponse<List<WordLearnedResponse>> getByReview(@RequestParam(value = "isReview") Boolean isReview) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.filterByReviewAndLearned(isReview, true);

        List<WordLearnedResponse> wordLearnedResponses = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get list word learned by review.")
                .data(wordLearnedResponses)
                .build();
    }

    @GetMapping(value = "/get-by-collection")
    public ApiStructResponse<List<WordLearnedResponse>> getByCollection(@RequestParam(value = "collectionId") String collectionId) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.filterByCollectionId(collectionId);

        List<WordLearnedResponse> wordLearnedResponses = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get list word learned by collectionId.")
                .data(wordLearnedResponses)
                .build();
    }

    @GetMapping(value = "/get-top-5")
    public ApiStructResponse<List<WordLearnedResponse>> getTop5(@RequestParam(value = "isReview") Boolean isReview) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.getTop5ByReview(isReview);

        List<WordLearnedResponse> wordLearnedResponses = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get top 5 word learned by review.")
                .data(wordLearnedResponses)
                .build();
    }

    @GetMapping(value = "/get-list-non-learned")
    public ApiStructResponse<List<WordLearnedResponse>> getByReviewAndLearned(@RequestParam(value = "isReview") Boolean isReview,
                                                                              @RequestParam(value = "isLearn") Boolean isLearn,
                                                                              @RequestParam(value = "collectionId") String collectionId) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.getNonActiveInCollection(isReview, isLearn, collectionId);

        List<WordLearnedResponse> wordLearnedResponses = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get list word learned by review and learned in collection.")
                .data(wordLearnedResponses)
                .build();
    }
}
