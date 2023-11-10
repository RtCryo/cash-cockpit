package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TransactionTypeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeDAO, UUID> {

    Optional<TransactionTypeDAO> findByType(String type);

}
