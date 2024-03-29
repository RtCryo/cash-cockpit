package org.myproject.cash.cockpit.handler.service.parser;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myproject.cash.cockpit.handler.BdTestContainer;
import org.myproject.cash.cockpit.handler.model.Destination;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.TransactionInfo;
import org.myproject.cash.cockpit.handler.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockBean(classes = {KafkaTemplate.class})
@SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
class TransactionParserFromCsvTest extends BdTestContainer {

    @Autowired
    private TransactionParserFromCsv parser;

    @Test
    @SneakyThrows
    void getTransactionListFromByteAndExpectTransaction() {
        Path path = new ClassPathResource("Kontoumsaetze_367_6139117_00_20231218_105036.csv").getFile().toPath();
        byte[] bytes = Files.readAllBytes(path);
        Transaction expected = Transaction.builder()
                .transactionType(TransactionType.builder()
                        .type("Kartenzahlung")
                        .build())
                .transactionDate(LocalDate.of(2023, 12, 18))
                .destination(Destination.builder()
                        .name("LIDL SAGT DANKE")
                        .build())
                .transactionInfo(TransactionInfo.builder()
                        .info("LIDL SAGT DANKE//Grasberg/DE 16-12-2023T11:47:04  Folgenr. 04  Verfalld. 1226")
                        .build())
                .total(-228.18)
                .tags(null)
                .build();

        List<Transaction> actual = parser.getTransactionListFromByte(bytes);

        assertThat(actual)
                .isNotNull()
                .hasSize(5)
                .contains(expected);
    }

    @Test
    @SneakyThrows
    void getTransactionListFromByte() {
        Path path = new ClassPathResource("Kontoumsaetze_367_6139117_00_20240315_210201.csv").getFile().toPath();
        byte[] bytes = Files.readAllBytes(path);

        List<Transaction> actual = parser.getTransactionListFromByte(bytes);

        assertThat(actual)
                .isNotNull()
                .hasSize(246);
    }
}