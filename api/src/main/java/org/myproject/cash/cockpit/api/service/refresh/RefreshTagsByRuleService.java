package org.myproject.cash.cockpit.api.service.refresh;


import org.myproject.cash.cockpit.api.repository.model.HasDAO;
import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.api.service.rule.RuleRepositoryService;
import org.myproject.cash.cockpit.api.service.transaction.TransactionRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RefreshTagsByRuleService {

    private final Map<String, RefreshTagsByArea> tagsByAreaMap;
    private final RuleRepositoryService ruleRepositoryService;
    private final TransactionRepositoryService transactionRepositoryService;

    public RefreshTagsByRuleService(final RuleRepositoryService ruleRepositoryService,
                                    final List<RefreshTagsByArea> tagsByArea, TransactionRepositoryService transactionRepositoryService) {
        this.ruleRepositoryService = ruleRepositoryService;
        this.tagsByAreaMap = tagsByArea.stream()
                .collect(Collectors.toMap(RefreshTagsByArea::getAreaType, Function.identity()));
        this.transactionRepositoryService = transactionRepositoryService;
    }

    public void refreshAndSaveTransaction(final Set<UUID> ruleIds) {
        List<TransactionDAO> refreshedTransactions = refresh(
                ruleRepositoryService.findAllById(ruleIds),
                transactionRepositoryService.findAll());
        transactionRepositoryService.saveAll(refreshedTransactions);
    }

    private List<TransactionDAO> refresh(final List<RuleDAO> ruleDAOS, final List<TransactionDAO> transactionDAO) {
        transactionDAO.forEach(transaction -> {
            for (RuleDAO ruleDAO : ruleDAOS) {
                if (isMatch(getHasString(ruleDAO), getAreaByType(ruleDAO.getArea().name(), transaction))) {
                    transaction.getTags().add(ruleDAO.getTag());
                }
            }
        });
        return transactionDAO;
    }

    private String getAreaByType(final String areaType, final TransactionDAO transactionDAO) {
        return tagsByAreaMap.get(areaType).refresh(transactionDAO);
    }

    private List<String> getHasString(RuleDAO ruleDAO) {
        return ruleDAO.getHas().stream().map(HasDAO::getHas).toList();
    }

    private boolean isMatch(final List<String> has, final String match) {
        return has.stream().allMatch(s -> match.toLowerCase().contains(s.toLowerCase()));
    }

}
