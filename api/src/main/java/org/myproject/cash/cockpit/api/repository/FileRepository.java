package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.FileDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface FileRepository extends ListCrudRepository<FileDAO, UUID> {

    long countByUserDAO(UserDAO userDAO);

}
