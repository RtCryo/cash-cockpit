package org.myproject.cash.cockpit.handler.repository;

import org.myproject.cash.cockpit.handler.repository.model.TransactionTypeDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionTypeRepository extends ListCrudRepository<TransactionTypeDAO, UUID> {

    Optional<TransactionTypeDAO> findByUserIdAndType(UUID userId, String type);

}
