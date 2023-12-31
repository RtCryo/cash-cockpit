package org.myproject.cash.cockpit.messenger.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.myproject.cash.cockpit.messenger.model.ReportDto;
import org.myproject.cash.cockpit.messenger.service.MessengerRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaStreamProcessor {

    @Value("${cash.cockpit.kafka.topic.consumer.name}")
    private String consumerTopic;

    private final MessengerRepositoryService service;
    private final StreamsBuilder streamsBuilder;

    private final JsonSerializer<ReportDto> jsonSerializer;
    private final JsonDeserializer<ReportDto> jsonDeserializer;

    @PostConstruct
    public void streamTopology() {
        KStream<String, ReportDto> stream = streamsBuilder
                .stream(consumerTopic, Consumed.with(Serdes.String(), Serdes.serdeFrom(jsonSerializer, jsonDeserializer)));

        stream.foreach((key, value) -> service.createMessage(value));
    }

}
