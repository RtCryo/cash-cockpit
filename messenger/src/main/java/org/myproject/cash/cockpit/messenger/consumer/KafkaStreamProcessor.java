package org.myproject.cash.cockpit.messenger.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.myproject.cash.cockpit.messenger.model.ReportDto;
import org.myproject.cash.cockpit.messenger.service.MessengerRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaStreamProcessor {

    @Value("${cash.cockpit.kafka.topic.consumer.name}")
    private String consumerTopic;

    private final MessengerRepositoryService service;
    private final StreamsBuilder streamsBuilder;

    @PostConstruct
    public void streamTopology() {
        KStream<String, ReportDto> stream = streamsBuilder
                .stream(consumerTopic, Consumed.with(Serdes.String(), Serdes.serdeFrom(ReportDto.class)));

        stream.foreach((key, value) -> service.createMessage(value));
    }

}
