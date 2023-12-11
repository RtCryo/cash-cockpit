package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.*;

public interface RuleRepository extends ListCrudRepository<RuleDAO, UUID> {

    void deleteAllByUserDAOAndTagIn(UserDAO userDAO, Collection<TagDAO> tagDAO);

    List<RuleDAO> findAllByUserDAO(UserDAO userDAO);

    boolean existsByUserDAOAndId(UserDAO userDAO, UUID id);

    Optional<RuleDAO> findAllByUserDAOAndId(UserDAO userDAO, UUID id);

}
