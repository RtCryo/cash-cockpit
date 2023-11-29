package org.myproject.cash.cockpit.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${cash.cockpit.kafka.topic.producer.name}")
    private String topicName;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void send(final UUID fileId, final byte[] fileToHandling) {
        kafkaTemplate.send(topicName, fileId.toString(), fileToHandling);
    }

}
