package org.myproject.cash.cockpit.api.service.file;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.rest.model.FileDTO;
import org.myproject.cash.cockpit.api.rest.model.FileInfoDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepositoryService fileRepositoryService;
    private final FileInfoRepositoryService fileInfoRepositoryService;
    private final ToDTOMapper mapper;

    public List<FileInfoDTO> findAll() {
        return fileInfoRepositoryService.findAll(Sort.by(Sort.Direction.DESC, "start")).stream()
                .map(mapper::toFileInfoDTO).toList();
    }

    public FileDTO getFileById(final UUID bankStatementId) {
        return mapper.toFileDTO(fileRepositoryService.findById(bankStatementId));
    }
}
