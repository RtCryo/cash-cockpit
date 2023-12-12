package org.myproject.cash.cockpit.api.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.service.model.FileInfoFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${cash.cockpit.kafka.topic.producer.name}")
    private String topicName;
    private final KafkaTemplate<String, FileInfoFacade> kafkaTemplate;

    public void send(final FileInfoFacade fileInfoFacade) {
        kafkaTemplate.send(topicName, fileInfoFacade);
    }
}
