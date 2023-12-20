package org.myproject.cash.cockpit.handler.service.enricher;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.repository.ConsumerRepository;
import org.myproject.cash.cockpit.handler.repository.TransactionTypeRepository;
import org.myproject.cash.cockpit.handler.repository.model.ConsumerDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionTypeDAO;
import org.myproject.cash.cockpit.handler.service.enricher.tag.RefreshTagsByRuleService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionEnricher {

    private final ConsumerRepository consumerRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final RefreshTagsByRuleService refreshTagsByRuleService;

    public TransactionDAO enrichTransaction(final TransactionDAO transactionDAO, final UUID userId) {
        transactionDAO.setUserId(userId);
        transactionDAO.setConsumer(getDestinationWhenExist(userId, transactionDAO.getConsumer().getName()));
        transactionDAO.setTransactionType(getTypeWhenExist(userId, transactionDAO.getTransactionType().getType()));
        refreshTagsByRuleService.refreshAndSaveTransaction(transactionDAO);
        return transactionDAO;
    }

    private ConsumerDAO getDestinationWhenExist(final UUID userId, final String name) {
        return consumerRepository.findByUserIdAndName(userId, name)
                .orElseGet(() -> createNewDestination(userId, name));
    }

    private TransactionTypeDAO getTypeWhenExist(final UUID userId, final String name) {
        return transactionTypeRepository.findByUserIdAndType(userId, name)
                .orElseGet(() -> createNewType(userId, name));
    }

    private ConsumerDAO createNewDestination(final UUID userId, final String name) {
        return consumerRepository.save(ConsumerDAO.builder()
                .userId(userId)
                .name(name)
                .build());
    }

    private TransactionTypeDAO createNewType(final UUID userId, final String name) {
        return transactionTypeRepository.save(TransactionTypeDAO.builder()
                .userId(userId)
                .type(name)
                .build());
    }

}
