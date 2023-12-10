package org.myproject.cash.cockpit.handler.service.parser;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.exception.ReadInputStreamException;
import org.myproject.cash.cockpit.handler.mapper.XmlToServiceModelMapper;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.xml.Document;
import org.myproject.cash.cockpit.handler.service.reader.XmlReader;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionParserFromXml extends TransactionParser {

    private final XmlReader xmlReader;
    private final XmlToServiceModelMapper serviceMapper;

    @Override
    public String getParserName() {
        return "text/xml";
    }

    @Override
    public List<Transaction> getTransactionListFromByte(final byte[] bytes) {
        Document document;
        try {
            document = xmlReader.readXml(bytes);
            List<Transaction> result = document.getBkToCstmrAcctRpt().getRpt().getNtryList().stream()
                    .map(serviceMapper::toTransactionFromXml)
                    .sorted(Comparator.comparing(Transaction::getTransactionDate))
                    .toList();
            localDateTransactionStart = result.getFirst().getTransactionDate();
            localDateTransactionEnd = result.getLast().getTransactionDate();
            return result;
        } catch (Exception e) {
            //todo add error message to fileInfo
            throw new ReadInputStreamException(e);
        }
    }
}
