package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.service.file.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final FileService fileService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageDTO importFile(@RequestParam("files") final List<MultipartFile> files) {
        fileService.importFile(files);
        return MessageDTO.builder()
                .code(0)
                .message("process started")
                .build();
    }

}
