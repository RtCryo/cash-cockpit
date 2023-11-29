package org.myproject.cash.cockpit.api.service.file;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.CreateFileDAOException;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.model.FileDAO;
import org.myproject.cash.cockpit.api.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.api.rest.model.FileDTO;
import org.myproject.cash.cockpit.api.rest.model.FileInfoDTO;
import org.myproject.cash.cockpit.api.service.KafkaProducer;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepositoryService fileRepositoryService;
    private final FileInfoRepositoryService fileInfoRepositoryService;
    private final KafkaProducer kafkaProducer;
    private final ToDTOMapper mapper;

    public List<FileInfoDTO> findAll() {
        return fileInfoRepositoryService.findAll(Sort.by(Sort.Direction.DESC, "start")).stream()
                .map(mapper::toFileInfoDTO).toList();
    }

    public FileDTO getFileById(final UUID bankStatementId) {
        return mapper.toFileDTO(fileRepositoryService.findById(bankStatementId));
    }

    public void importFile(final List<MultipartFile> files) {
        files.forEach(multipartFile -> {
            FileDAO fileDAO = getFileDAOFromMultipartFile(multipartFile);
            FileDAO saved = fileRepositoryService.save(fileDAO);
            FileInfoDAO fileInfoDAO = getFileInfoDAO(multipartFile, saved);
            fileInfoRepositoryService.save(fileInfoDAO);
            kafkaProducer.send(Objects.requireNonNull(saved.getId()), saved.getFileByte());
        });
    }

    private FileInfoDAO getFileInfoDAO(final MultipartFile multipartFile, final FileDAO saved) {
        return FileInfoDAO.builder()
                .start(null)
                .end(null)
                .isHandled(false)
                .type(multipartFile.getContentType())
                .name(multipartFile.getOriginalFilename())
                .bankStatement(saved)
                .build();
    }

    private FileDAO getFileDAOFromMultipartFile(final MultipartFile multipartFile) {
        try {
            return FileDAO.builder()
                    .fileByte(multipartFile.getBytes())
                    .build();
        } catch (IOException e) {
            throw new CreateFileDAOException(e);
        }
    }

    public void updateFileInfoFromBroker(final UUID id, final LocalDate start, final LocalDate end) {
        FileInfoDAO infoDAO = fileInfoRepositoryService.findById(id);
        infoDAO.setStart(start);
        infoDAO.setEnd(end);
        infoDAO.setIsHandled(true);
        fileInfoRepositoryService.save(infoDAO);
    }
}
