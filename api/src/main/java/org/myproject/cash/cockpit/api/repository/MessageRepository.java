package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.MessageDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends ListCrudRepository<MessageDAO, UUID> {

    List<MessageDAO> findAllByUserAndIsNew(UserDAO userDAO, boolean isNew);

}
