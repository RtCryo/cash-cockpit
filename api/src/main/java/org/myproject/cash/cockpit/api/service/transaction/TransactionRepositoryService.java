package org.myproject.cash.cockpit.api.service.transaction;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.TransactionRepository;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryService {

    private final TransactionRepository transactionRepository;

    public long count() {
        return transactionRepository.count();
    }

    public List<TransactionDAO> findAllByTagId(final Iterable<TagDAO> tagIdToDelete) {
        return transactionRepository.findAllByTagsIn(tagIdToDelete);
    }

    public void saveAll(final List<TransactionDAO> updatedList) {
        transactionRepository.saveAll(updatedList);
    }
}
