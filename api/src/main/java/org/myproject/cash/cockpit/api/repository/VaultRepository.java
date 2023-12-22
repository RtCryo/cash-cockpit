package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.myproject.cash.cockpit.api.repository.model.VaultDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface VaultRepository extends ListCrudRepository<VaultDAO, UUID> {

    List<VaultDAO> findAllByUserDAO(UserDAO userDAO);

}
