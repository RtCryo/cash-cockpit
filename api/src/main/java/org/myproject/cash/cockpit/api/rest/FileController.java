package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.FileInfoDTO;
import org.myproject.cash.cockpit.api.service.file.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/statement")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<FileInfoDTO> getBankStatements() {
        return fileService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{bankStatementId}")
    public byte[] getBankStatementById(@PathVariable final UUID bankStatementId) {
        return fileService.getFileById(bankStatementId).fileByte();
    }

}
