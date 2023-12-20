package org.myproject.cash.cockpit.handler.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myproject.cash.cockpit.handler.model.Destination;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.TransactionInfo;
import org.myproject.cash.cockpit.handler.model.TransactionType;
import org.myproject.cash.cockpit.handler.repository.model.ConsumerDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionInfoDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionTypeDAO;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ServiceModelToDaoMapperTest {

    ServiceModelToDaoMapper mapper = Mappers.getMapper(ServiceModelToDaoMapper.class);

    @Test
    void toDao() {
        Transaction input = Transaction.builder()
                .total(1234)
                .destination(Destination.builder().name("HKK").build())
                .transactionDate(LocalDate.of(2023, 12, 1))
                .transactionType(TransactionType.builder().type("Type").build())
                .transactionInfo(TransactionInfo.builder().info("Info").build())
                .build();

        TransactionDAO expected = TransactionDAO.builder()
                .total(1234)
                .consumer(ConsumerDAO.builder().name("HKK").build())
                .transactionDate(LocalDate.of(2023, 12, 1))
                .transactionType(TransactionTypeDAO.builder().type("Type").build())
                .transactionInfo(TransactionInfoDAO.builder().info("Info").build())
                .tags(Collections.emptySet())
                .build();

        TransactionDAO actual = mapper.toDao(input);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}