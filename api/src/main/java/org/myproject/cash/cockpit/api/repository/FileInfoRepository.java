package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.FileInfoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileInfoRepository extends JpaRepository<FileInfoDAO, UUID> {

    FileInfoDAO findByName(String name);

}
