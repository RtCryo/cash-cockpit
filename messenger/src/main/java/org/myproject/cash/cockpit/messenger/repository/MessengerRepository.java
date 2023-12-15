package org.myproject.cash.cockpit.messenger.repository;

import org.myproject.cash.cockpit.messenger.repository.model.MessageDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface MessengerRepository extends ListCrudRepository<MessageDAO, UUID> {
}
