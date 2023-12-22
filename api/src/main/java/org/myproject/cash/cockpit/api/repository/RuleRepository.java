package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface RuleRepository extends ListCrudRepository<RuleDAO, UUID> {

    void deleteAllByUserDAOAndTagIn(UserDAO userDAO, Collection<TagDAO> tagDAO);

    List<RuleDAO> findAllByUserDAO(UserDAO userDAO);

    List<RuleDAO> findAllByUserDAOAndIdIn(UserDAO userDAO, Collection<UUID> ids);

}
