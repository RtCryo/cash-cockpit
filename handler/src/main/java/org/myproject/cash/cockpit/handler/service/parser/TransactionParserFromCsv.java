package org.myproject.cash.cockpit.handler.service.parser;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.mapper.CsvToServiceModelMapper;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.service.reader.CsvReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionParserFromCsv extends TransactionParser {

    private final CsvReader reader;
    private final CsvToServiceModelMapper mapper;

    @Override
    public String getParserName() {
        return "text/csv";
    }

    @Override
    public List<Transaction> getTransactionListFromByte(final byte[] bytes) {

        List<Transaction> result = reader.readCsv(bytes).stream().map(mapper::toTransaction).toList();

        localDateTransactionStart = result.getFirst().getTransactionDate();
        localDateTransactionEnd = result.getLast().getTransactionDate();

        return result;
    }
}
