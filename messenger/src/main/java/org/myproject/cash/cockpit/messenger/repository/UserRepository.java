package org.myproject.cash.cockpit.messenger.repository;


import org.myproject.cash.cockpit.messenger.repository.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {

}
