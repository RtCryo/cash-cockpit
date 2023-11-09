package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface RuleRepository extends ListCrudRepository<RuleDAO, UUID> {
}
