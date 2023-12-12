package org.myproject.cash.cockpit.handler.repository;

import org.myproject.cash.cockpit.handler.repository.model.ConsumerDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerRepository extends ListCrudRepository<ConsumerDAO, UUID> {

    Optional<ConsumerDAO> findByUserIdAndName(UUID userId, String name);

}
