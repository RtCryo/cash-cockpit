package org.myproject.cash.cockpit.handler.repository;


import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends ListCrudRepository<TransactionDAO, UUID> {

    List<TransactionDAO> findAllByUserIdAndTransactionDateBetweenOrderByTransactionDate(UUID userId, LocalDate start, LocalDate end);

}
