package org.myproject.cash.cockpit.handler.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.exception.FileInfoNotFoundException;
import org.myproject.cash.cockpit.handler.repository.FileInfoRepository;
import org.myproject.cash.cockpit.handler.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.handler.repository.model.ProgressStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileInfoRepositoryService {

    private final FileInfoRepository fileInfoRepository;

    public FileInfoDAO findFileInfoById(final UUID id) {
        return fileInfoRepository.findById(id)
                .orElseThrow(() -> new FileInfoNotFoundException(id.toString(), "FileInfo not found. ID: " + id));
    }

    public void updateAndSaveFileInfo(final FileInfoDAO fileInfoDAO, final LocalDate start, final LocalDate end) {
        enchantFileInfo(fileInfoDAO, start, end);
        fileInfoRepository.save(fileInfoDAO);
    }

    private void enchantFileInfo(final FileInfoDAO fileInfoDAO, final LocalDate start, final LocalDate end) {
        fileInfoDAO.setStart(start);
        fileInfoDAO.setEnd(end);
        fileInfoDAO.setIsHandled(true);
        fileInfoDAO.setStatus(ProgressStatus.DONE);
    }

    public boolean isExist(final UUID key) {
        return fileInfoRepository.existsById(key);
    }

    public void save(final FileInfoDAO fileInfoDAO) {
        fileInfoRepository.save(fileInfoDAO);
    }
}
