package org.myproject.cash.cockpit.api.rest;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.rest.model.VaultDTO;
import org.myproject.cash.cockpit.api.service.vault.VaultRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vault")
@RequiredArgsConstructor
public class VaultController {

    private final VaultRepositoryService vaultService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VaultDTO> showVault() { return vaultService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO saveVault(@RequestBody @Min(value = 1, message = "The amount is too small") double sum) {
        vaultService.saveVault(sum);
        return MessageDTO.create(0, "Amount saved");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO deleteVault(@PathVariable final UUID id) {
        vaultService.deleteVault(id);
        return MessageDTO.create(0, "Amount deleted");
    }

}
