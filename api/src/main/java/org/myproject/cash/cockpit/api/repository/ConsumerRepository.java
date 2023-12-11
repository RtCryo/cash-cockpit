package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.ConsumerDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface ConsumerRepository extends ListCrudRepository<ConsumerDAO, UUID> {

    List<ConsumerDAO> findAllByUserDAO(UserDAO userDAO);

    long countByUserDAO(UserDAO userDAO);
}
