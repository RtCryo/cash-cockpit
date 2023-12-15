package org.myproject.cash.cockpit.handler.repository;

import org.myproject.cash.cockpit.handler.repository.model.SenderDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SenderRepository extends ListCrudRepository<SenderDAO, UUID> {

    Optional<SenderDAO> findByUserIdAndName(UUID userId, String name);

}
