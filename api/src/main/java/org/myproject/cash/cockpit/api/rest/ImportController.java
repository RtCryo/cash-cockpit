package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.FileInfoDTO;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.service.file.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final FileService fileService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO importFile(@RequestParam("files") final List<MultipartFile> files) {
        //todo
        return MessageDTO.builder()
                .code(0)
                .message("TODO")
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/statement")
    public List<FileInfoDTO> getBankStatements() {
        return fileService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/statement/{bankStatementId}")
    public byte[] getBankStatementById(@PathVariable final UUID bankStatementId) {
        return fileService.getFileById(bankStatementId).getFileByte();
    }

}
