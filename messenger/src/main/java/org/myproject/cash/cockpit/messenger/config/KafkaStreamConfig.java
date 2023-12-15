package org.myproject.cash.cockpit.messenger.config;

import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.streams.StreamsConfig;
import org.myproject.cash.cockpit.messenger.model.ReportDto;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Bean
    public StreamsConfig streamsConfig(final KafkaProperties kafkaProperties, final DefaultSslBundleRegistry sslBundleRegistry) {
        return new StreamsConfig(kafkaProperties.buildStreamsProperties(sslBundleRegistry));
    }

    @Bean
    public JsonSerializer<ReportDto> jsonSerializer() {
        return new JsonSerializer<>();
    }

    @Bean
    public JsonDeserializer<ReportDto> jsonDeserializer(Jackson2JavaTypeMapper typeMapper) {
        JsonDeserializer<ReportDto> reportDtoJsonDeserializer = new JsonDeserializer<>(ReportDto.class);
        reportDtoJsonDeserializer.setTypeMapper(typeMapper);
        return reportDtoJsonDeserializer;
    }

    @Bean
    public Jackson2JavaTypeMapper typeMapper(){
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("*");
        typeMapper.fromClass(ReportDto.class, new RecordHeaders());
        return typeMapper;
    }


}
