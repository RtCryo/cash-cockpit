package org.myproject.cash.cockpit.handler.service.enricher.tag;

import org.myproject.cash.cockpit.handler.repository.RuleRepository;
import org.myproject.cash.cockpit.handler.repository.model.HasDAO;
import org.myproject.cash.cockpit.handler.repository.model.RuleDAO;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RefreshTagsByRuleService {

    private final Map<String, RefreshTagsByArea> tagsByAreaMap;
    private final RuleRepository ruleRepository;

    public RefreshTagsByRuleService(final RuleRepository ruleRepository,
                                    final List<RefreshTagsByArea> tagsByArea) {
        this.ruleRepository = ruleRepository;
        this.tagsByAreaMap = tagsByArea.stream()
                .collect(Collectors.toMap(RefreshTagsByArea::getAreaType, Function.identity()));
    }

    public void refreshAndSaveTransaction(final TransactionDAO transactionDAO) {
        refresh(ruleRepository.findAllByUserId(transactionDAO.getUserId()),
                transactionDAO);
    }

    private void refresh(final List<RuleDAO> ruleDAOS, final TransactionDAO transactionDAO) {
        for (RuleDAO ruleDAO : ruleDAOS) {
            if (isMatch(getHasString(ruleDAO), getAreaByType(ruleDAO.getArea().name(), transactionDAO))) {
                transactionDAO.getTags().add(ruleDAO.getTag());
            }
        }
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
