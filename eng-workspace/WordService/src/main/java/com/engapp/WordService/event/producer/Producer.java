package com.engapp.WordService.event.producer;

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

    public void sendMessageToPartition(String topic, T event) {
        kafkaTemplate.send(topic, event.toString() ,event);
    }
}
