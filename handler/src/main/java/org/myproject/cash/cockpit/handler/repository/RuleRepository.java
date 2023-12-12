package org.myproject.cash.cockpit.handler.repository;

import org.myproject.cash.cockpit.handler.repository.model.RuleDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface RuleRepository extends ListCrudRepository<RuleDAO, UUID> {

    List<RuleDAO> findAllByUserId(UUID userId);

}
