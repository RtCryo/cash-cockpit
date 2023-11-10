package org.myproject.cash.cockpit.api.service.transaction;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.TransactionTypeRepository;
import org.myproject.cash.cockpit.api.repository.model.TransactionTypeDAO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionTypeRepositoryService {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeDAO findByType(final String type) {
        return transactionTypeRepository.findByType(type)
                .orElseGet(this::createCustomType);
    }

    private TransactionTypeDAO createCustomType() {
        return transactionTypeRepository.save(
                TransactionTypeDAO.builder()
                        .type("CUSTOM")
                        .build());
    }
}
