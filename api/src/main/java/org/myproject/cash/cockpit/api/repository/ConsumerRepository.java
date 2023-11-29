package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.ConsumerDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ConsumerRepository extends ListCrudRepository<ConsumerDAO, UUID> {
}
