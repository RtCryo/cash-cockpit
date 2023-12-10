package org.myproject.cash.cockpit.handler.consumer;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.service.HandleProcess;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DataListener {

    private final HandleProcess handleProcess;

    @KafkaListener(topics = "${cash.cockpit.kafka.topic.consumer.name}", groupId = "${cash.cockpit.kafka.group.id}")
    public void listen(@Header(KafkaHeaders.RECEIVED_KEY) String key, final byte[] bytes) {
        handleProcess.run(key, bytes);
    }

}