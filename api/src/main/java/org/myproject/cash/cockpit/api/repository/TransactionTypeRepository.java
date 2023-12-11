package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TransactionTypeDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeDAO, UUID> {

    Optional<TransactionTypeDAO> findByUserDAOAndType(UserDAO userDAO, String type);

    List<TransactionTypeDAO> findAllByUserDAO(UserDAO userDAO);

}
