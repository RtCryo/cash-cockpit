package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FileInfoRepository extends JpaRepository<FileInfoDAO, UUID> {

    List<FileInfoDAO> findAllByUserDAO(UserDAO userDAO, Sort sort);

}
