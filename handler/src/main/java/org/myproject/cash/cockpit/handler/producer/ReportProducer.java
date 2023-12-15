package org.myproject.cash.cockpit.handler.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportProducer {

    @Value("${cash.cockpit.kafka.topic.producer.name}")
    private String topicName;
    private final KafkaTemplate<String, ReportDto> kafkaTemplate;

    public void send(final ReportDto reportDto) {
        kafkaTemplate.send(topicName, reportDto);
    }

}
