package org.myproject.cash.cockpit.messenger.config;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Bean
    public StreamsConfig streamsConfig(final KafkaProperties kafkaProperties, final DefaultSslBundleRegistry sslBundleRegistry) {
        return new StreamsConfig(kafkaProperties.buildStreamsProperties(sslBundleRegistry));
    }


}
