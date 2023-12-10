package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.MessageDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends ListCrudRepository<MessageDAO, UUID> {

    List<MessageDAO> findAllByUserId(final UUID userId);

}
