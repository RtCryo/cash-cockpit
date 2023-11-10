package org.myproject.cash.cockpit.api.rest.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TransactionDTO(@NotNull LocalDate transactionDate,
                             @NotNull TransactionInfoDTO transactionInfo,
                             TransactionTypeDTO transactionType,
                             @NotNull DestinationDTO destination,
                             @NotNull SenderDTO sender,
                             List<TagDTO> tags,
                             @NotNull Double total) {
}
