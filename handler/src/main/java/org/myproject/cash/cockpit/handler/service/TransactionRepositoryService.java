package org.myproject.cash.cockpit.handler.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.mapper.DaoToServiceModelMapper;
import org.myproject.cash.cockpit.handler.mapper.ServiceModelToDaoMapper;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.repository.TransactionRepository;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.service.enricher.TransactionEnricher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryService {

    private final TransactionRepository repository;
    private final ServiceModelToDaoMapper mapper;
    private final DaoToServiceModelMapper serviceModelMapper;
    private final TransactionEnricher transactionEnricher;

    public List<TransactionDAO> saveTransactions(final List<Transaction> transactions, final UUID userId, final LocalDate localDateTransactionStart, final LocalDate localDateTransactionEnd) {
        List<Transaction> transactionFromDb = repository
                .findAllByUserIdAndTransactionDateBetweenOrderByTransactionDate(userId, localDateTransactionStart, localDateTransactionEnd)
                .stream()
                .map(serviceModelMapper::toTransaction)
                .toList();

        List<TransactionDAO> newTransactions = transactions.stream()
                .filter(transaction -> !isExist(transaction, transactionFromDb))
                .map(mapper::toDao)
                .map(transactionDAO -> transactionEnricher.enrichTransaction(transactionDAO, userId))
                .toList();

        return repository.saveAll(newTransactions);
    }

    private boolean isExist(final Transaction transaction, final List<Transaction> transactionList) {
        if (transactionList.isEmpty()) {
            return false;
        }
        return transactionList.stream().anyMatch(transactionFromDb -> transactionFromDb.equals(transaction));
    }
}
