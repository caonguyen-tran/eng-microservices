package com.engapp.WordService.controller;

import com.engapp.WordService.dto.request.WordLearnedRequest;
import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordLearnedResponse;
import com.engapp.WordService.event.DownloadEvent;
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
@RequestMapping(value="/learned-word")
@Slf4j
public class WordLearnedController {
    @Autowired
    private WordLearnedService wordLearnedService;

    @Autowired
    private WordLearnedMapper wordLearnedMapper;

    @Autowired
    private WordService wordService;

    @PostMapping(value="/learn-first-time")
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

    @PatchMapping(value="/update-list-word")
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
    public void listenCollectionDownloadEvent(DownloadEvent downloadEvent){
        List<Word> listWords = this.wordService.getListWordByCollectionId(downloadEvent.getCollectionId());
        this.wordLearnedService.downloadListWordInCollection(listWords, downloadEvent.getUserId());
        log.info("Download collection id is {} of user id {}", downloadEvent.getCollectionId(), downloadEvent.getUserId());
    }

    @GetMapping(value="/get-lte-due-date")
    public ApiStructResponse<List<WordLearnedResponse>> getByDueDateLte(){
        List<WordLearned> wordLearnedList = this.wordLearnedService.filterByDueDateLessThanOrEqual(Instant.now());
        List<WordLearnedResponse> wordLearnedResponseList = wordLearnedList.stream().map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item)).toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Get list word learned by due date less than or equal NOW().")
                .data(wordLearnedResponseList)
                .build();
    }

    @KafkaListener(topics="update-review", groupId = "handle-event-group", containerFactory = "kafkaListenerWordLearnedContainerFactory")
    public void listenUpdateViewEvent(WordLearned wordLearned){
        if(wordLearned.isReview()){
            if(wordLearned.getDueDate().isBefore(Instant.now())){
                this.wordLearnedService.updateReviewStatus(wordLearned);
            }
        }
    }
}
