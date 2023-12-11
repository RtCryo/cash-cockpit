package org.myproject.cash.cockpit.api.service.file;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.FileInfoRepository;
import org.myproject.cash.cockpit.api.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileInfoRepositoryService {

    private final FileInfoRepository fileInfoRepository;

    public List<FileInfoDAO> findAll(final Sort sort) {
        return fileInfoRepository.findAllByUserDAO(UserService.getUser(), sort);
    }

    public FileInfoDAO save(final FileInfoDAO fileInfoDAO) {
        return fileInfoRepository.save(fileInfoDAO);
    }
}
