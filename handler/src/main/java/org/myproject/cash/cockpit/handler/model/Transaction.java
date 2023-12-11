package org.myproject.cash.cockpit.handler.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Transaction {

    @NotNull
    @Builder.Default
    private final LocalDate transactionDate = LocalDate.now();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    private final TransactionInfo transactionInfo = TransactionInfo.builder().build();

    @Builder.Default
    private final TransactionType transactionType = TransactionType.builder().build();

    @Builder.Default
    private final Sender sender = Sender.builder().build();

    @Builder.Default
    private final Destination destination = Destination.builder().build();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    private final List<Tag> tags = new ArrayList<>();

    @NotNull
    private final double total;

}
