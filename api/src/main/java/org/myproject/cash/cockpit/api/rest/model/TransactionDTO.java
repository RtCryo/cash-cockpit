package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TransactionDTO(UUID id,
                             @NotNull LocalDate transactionDate,
                             @NotNull TransactionInfoDTO transactionInfo,
                             TransactionTypeDTO transactionType,
                             @NotNull ConsumerDTO consumer,
                             List<TagDTO> tags,
                             @NotNull Double total) {
}
