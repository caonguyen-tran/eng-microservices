package com.engapp.WordService.configuration;

import com.engapp.WordService.event.DownloadEvent;
import com.engapp.WordService.event.WordLearnedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DownloadEvent> kafkaListenerDownloadEventContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DownloadEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(downloadEventConsumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    public ConsumerFactory<String, DownloadEvent> downloadEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        JsonDeserializer<DownloadEvent> deserializer = new JsonDeserializer<>(DownloadEvent.class, false);
        deserializer.addTrustedPackages("*");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, WordLearnedEvent> kafkaListenerWordLearnedContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, WordLearnedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(wordLearnedConsumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    public ConsumerFactory<String, WordLearnedEvent> wordLearnedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        JsonDeserializer<WordLearnedEvent> deserializer = new JsonDeserializer<>(WordLearnedEvent.class, false);
        deserializer.addTrustedPackages("*");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ProducerFactory<String ,WordLearnedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, WordLearnedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
