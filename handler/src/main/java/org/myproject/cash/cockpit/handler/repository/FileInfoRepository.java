package org.myproject.cash.cockpit.handler.repository;

import org.myproject.cash.cockpit.handler.repository.model.FileInfoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileInfoRepository extends JpaRepository<FileInfoDAO, UUID> {

    FileInfoDAO findByName(String name);

}
