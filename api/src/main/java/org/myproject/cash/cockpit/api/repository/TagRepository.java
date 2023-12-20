package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends ListCrudRepository<TagDAO, UUID> {

    boolean existsByTagName(String tagName);

    long countByUserDAO(UserDAO userDAO);

    List<TagDAO> findAllByUserDAO(UserDAO userDAO);

    List<TagDAO> findAllByUserDAOAndAndRule(UserDAO userDAO, RuleDAO ruleDAO);

    Optional<TagDAO> findByUserDAOAndId(UserDAO userDAO, UUID id);

    void deleteAllByUserDAOAndId(UserDAO userDAO, UUID id);

    List<TagDAO> findAllByUserDAOAndIdIn(UserDAO userDAO, Collection<UUID> ids);
}
