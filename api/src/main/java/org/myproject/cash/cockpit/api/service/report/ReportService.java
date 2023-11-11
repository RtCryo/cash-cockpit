package org.myproject.cash.cockpit.api.service.report;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.ReportDTO;
import org.myproject.cash.cockpit.api.service.consumer.ConsumerRepositoryService;
import org.myproject.cash.cockpit.api.service.file.FileRepositoryService;
import org.myproject.cash.cockpit.api.service.rule.RuleRepositoryService;
import org.myproject.cash.cockpit.api.service.tag.TagRepositoryService;
import org.myproject.cash.cockpit.api.service.transaction.TransactionRepositoryService;
import org.myproject.cash.cockpit.api.service.vault.VaultRepositoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final FileRepositoryService fileRepositoryService;
    private final ConsumerRepositoryService consumerRepositoryService;
    private final TagRepositoryService tagRepositoryService;
    private final TransactionRepositoryService transactionRepositoryService;
    private final RuleRepositoryService ruleRepositoryService;
    private final VaultRepositoryService vaultRepositoryService;

    public ReportDTO getReport() {
        return ReportDTO.builder()
                .files(fileRepositoryService.count())
                .consumers(consumerRepositoryService.count())
                .tags(tagRepositoryService.count())
                .transactions(transactionRepositoryService.count())
                .rules(ruleRepositoryService.count())
                .vault(vaultRepositoryService.getTotalSum())
                .build();
    }
}
