package org.myproject.cash.cockpit.api.service.file;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.FileInfoNotFoundException;
import org.myproject.cash.cockpit.api.repository.FileInfoRepository;
import org.myproject.cash.cockpit.api.repository.model.FileInfoDAO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileInfoRepositoryService {

    private final FileInfoRepository fileInfoRepository;

    public List<FileInfoDAO> findAll(final Sort sort) {
        return fileInfoRepository.findAll(sort);
    }

    public FileInfoDAO save(final FileInfoDAO fileInfoDAO) {
        return fileInfoRepository.save(fileInfoDAO);
    }

    public FileInfoDAO findById(final UUID id) {
        return fileInfoRepository.findById(id)
                .orElseThrow(() -> new FileInfoNotFoundException("FileInfo id: " + id + " not exist"));
    }
}
