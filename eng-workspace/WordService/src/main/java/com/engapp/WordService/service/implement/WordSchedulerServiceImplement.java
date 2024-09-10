package com.engapp.WordService.service.implement;

import com.engapp.WordService.event.producer.Producer;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.service.WordLearnedService;
import com.engapp.WordService.service.WordSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class WordSchedulerServiceImplement implements WordSchedulerService {
    @Autowired
    private WordLearnedService wordLearnedService;

    @Autowired
    private Producer<WordLearned> producer;

    @Scheduled(fixedRate = 3600000)
    public void scheduled() {
        List<WordLearned> listWordLearned = this.wordLearnedService.filterByDueDateLessThanOrEqual(Instant.now());
        log.info("sent message to update review topic!");
        for(WordLearned wordLearned : listWordLearned) {
            producer.sendMessageToPartition("update-review", wordLearned);
        }
    }
}
