package org.myproject.cash.cockpit.api.service.file;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.FileIsNotExistException;
import org.myproject.cash.cockpit.api.repository.FileRepository;
import org.myproject.cash.cockpit.api.repository.model.FileDAO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileRepositoryService {

    private final FileRepository fileRepository;

    public FileDAO findById(final UUID bankStatementId) {
        return fileRepository.findById(bankStatementId)
                .orElseThrow(() -> new FileIsNotExistException("File is not exist. Id: " + bankStatementId));
    }

    public long count() {
        return fileRepository.countByUserDAO(UserService.getUser());
    }

    public FileDAO save(final FileDAO fileDAO) {
        return fileRepository.save(fileDAO);
    }
}
