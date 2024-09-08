package com.engapp.CollectionService.event.Producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer<T> {
    @Autowired
    private final KafkaTemplate<String, T> kafkaTemplate;

    public void sendMessage(String topic, T event) {
        kafkaTemplate.send(topic, event);
    }
}
