package org.myproject.cash.cockpit.handler.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.exception.FileInfoNotFoundException;
import org.myproject.cash.cockpit.handler.mapper.ServiceModelToDaoMapper;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.repository.FileInfoRepository;
import org.myproject.cash.cockpit.handler.repository.TransactionRepository;
import org.myproject.cash.cockpit.handler.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.service.parser.TransactionParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HandleProcess {

    private final TransactionParser parser;
    private final FileInfoRepository fileInfoRepository;
    private final TransactionRepository transactionRepository;
    private final ServiceModelToDaoMapper mapper;

    @Transactional
    public void run(final String key, final byte[] bytes) {
        FileInfoDAO fileInfoDAO = fileInfoRepository.findById(UUID.fromString(key))
                .orElseThrow(() -> new FileInfoNotFoundException("FileInfo not found. ID: " + key));

        List<Transaction> transactions = parser.getTransactionListFromByte(bytes);
        saveTransactions(transactions, fileInfoDAO.getUserId());
        enchantFileInfo(fileInfoDAO);
        fileInfoRepository.save(fileInfoDAO);
    }

    private void enchantFileInfo(final FileInfoDAO fileInfoDAO) {
        fileInfoDAO.setStart(parser.getLocalDateTransactionStart());
        fileInfoDAO.setEnd(parser.getLocalDateTransactionEnd());
        fileInfoDAO.setIsHandled(true);
    }

    private void saveTransactions(final List<Transaction> transactions, final UUID userId) {
        transactions.forEach(transaction -> {
            TransactionDAO transactionDAO = mapper.toDao(transaction);
            transactionDAO.setUserId(userId);
            transactionRepository.save(transactionDAO);
        });
    }
}
