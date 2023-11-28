package org.myproject.cash.cockpit.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myproject.cash.cockpit.api.exception.ConvertStringToModelObjectMapperException;
import org.myproject.cash.cockpit.api.service.file.FileService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final FileService fileService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "${cash.cockpit.kafka.topic.consumer.name}",
            groupId = "${cash.cockpit.kafka.group.id}")
    private void listenerBoxdata(final String fileInfo) {
        FileInfoFromKafkaModel fileInfoFromKafkaModel = getFileInfoFromKafkaModel(fileInfo);
        fileService.updateFileInfoFromBroker(
                fileInfoFromKafkaModel.id,
                fileInfoFromKafkaModel.start,
                fileInfoFromKafkaModel.end);
    }

    private FileInfoFromKafkaModel getFileInfoFromKafkaModel(final String fileInfo) {
        try {
            return mapper.readValue(fileInfo, FileInfoFromKafkaModel.class);
        } catch (JsonProcessingException e) {
            throw new ConvertStringToModelObjectMapperException(e);
        }
    }

    private record FileInfoFromKafkaModel(UUID id, UUID userId, LocalDate start, LocalDate end,
                                          Integer transactionCount) {
    }
}
