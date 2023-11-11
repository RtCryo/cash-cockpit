package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.TransactionTypeDTO;
import org.myproject.cash.cockpit.api.service.transaction.TransactionTypeRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TransactionTypeController {

    private final TransactionTypeRepositoryService transactionTypeRepositoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionTypeDTO> showAllCategory() {
        return transactionTypeRepositoryService.findAllTransactionTypes();
    }

}
