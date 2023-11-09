package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TransactionRepository extends ListCrudRepository<TransactionDAO, UUID> {
}
