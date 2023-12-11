package org.myproject.cash.cockpit.handler.service.parser;

import lombok.Getter;
import lombok.Setter;
import org.myproject.cash.cockpit.handler.model.Transaction;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public abstract class TransactionParser {

    protected LocalDate localDateTransactionStart = LocalDate.now();
    protected LocalDate localDateTransactionEnd = LocalDate.now();

    public abstract List<Transaction> getTransactionListFromByte(final byte[] bytes);

    public abstract String getParserName();

}
