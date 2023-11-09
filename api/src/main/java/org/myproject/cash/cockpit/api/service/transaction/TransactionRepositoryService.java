package org.myproject.cash.cockpit.api.service.transaction;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryService {

    private final TransactionRepository transactionRepository;

    public long count() {
        return transactionRepository.count();
    }
}
