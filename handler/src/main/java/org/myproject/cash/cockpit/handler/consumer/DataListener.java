package org.myproject.cash.cockpit.handler.consumer;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.service.HandleProcess;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataListener {

    private final HandleProcess handleProcess;

    @KafkaListener(topics = "${cash.cockpit.kafka.topic.consumer.name}", groupId = "${cash.cockpit.kafka.group.id}")
    public void listen(final FileInfoFacade fileInfoFacade) {
        handleProcess.run(fileInfoFacade);
    }

}