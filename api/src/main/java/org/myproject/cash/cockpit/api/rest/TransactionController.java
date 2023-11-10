package org.myproject.cash.cockpit.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.FilterDTO;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.rest.model.TransactionDTO;
import org.myproject.cash.cockpit.api.service.transaction.TransactionRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepositoryService transactionRepositoryService;

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> showTransactionWithDateFilter(@RequestBody final FilterDTO filter) {
        return transactionRepositoryService.findTransactionsWithFilter(
                filter.localDateStart(),
                filter.localDateEnd(),
                filter.tags());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO showTransaction(@PathVariable("id") final UUID id) {
        return transactionRepositoryService.findTransaction(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTransaction(@PathVariable("id") final UUID id, @RequestBody final TransactionDTO transaction) {
        transactionRepositoryService.updateTransaction(id, transaction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO saveTransaction(@RequestBody @Valid final TransactionDTO transaction) {
        return transactionRepositoryService.saveCustomTransaction(transaction);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO deleteTransaction(@PathVariable("id") final UUID id) {
        transactionRepositoryService.removeCustomTransaction(id);
        return MessageDTO.create(0, "Deleted");
    }

}
