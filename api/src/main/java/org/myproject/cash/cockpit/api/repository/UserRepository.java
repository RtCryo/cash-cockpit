package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {

    UserDAO findByUsername(String username);

}
