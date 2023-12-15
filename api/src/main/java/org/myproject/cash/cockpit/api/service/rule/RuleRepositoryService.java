package org.myproject.cash.cockpit.api.service.rule;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.RuleNotFoundException;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.RuleRepository;
import org.myproject.cash.cockpit.api.repository.model.RuleDAO;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.rest.model.RuleDTO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RuleRepositoryService {

    private final RuleRepository ruleRepository;
    private final ToDTOMapper toDTOMapper;
    private final ToDAOMapper toDAOMapper;

    public long count() {
        return ruleRepository.count();
    }

    public List<RuleDTO> getAllRules() {
        return ruleRepository.findAllByUserDAO(UserService.getCurrentUser())
                .stream()
                .map(toDTOMapper::toRuleDTO)
                .toList();
    }

    public void createRule(final RuleDTO ruleDto) {
        RuleDAO ruleDAO = toDAOMapper.toRuleDAO(ruleDto);
        ruleDAO.setUserDAO(UserService.getCurrentUser());
        ruleRepository.save(ruleDAO);
    }

    public void deleteAllRule(final List<UUID> ruleIds) {
        List<UUID> listToDelete = new ArrayList<>();
        ruleIds.forEach(uuid -> {
            if (ruleRepository.existsByUserDAOAndId(UserService.getCurrentUser(), uuid)) {
                listToDelete.add(uuid);
            }
        });
        ruleRepository.deleteAllById(listToDelete);
    }

    @Transactional
    public void update(final RuleDTO ruleDto, final UUID ruleId) {
        RuleDAO ruleToUpdate = ruleRepository.findAllByUserDAOAndId(UserService.getCurrentUser(), ruleId)
                .orElseThrow(RuleNotFoundException::new);
        RuleDAO newRule = toDAOMapper.toRuleDAO(ruleDto);
        ruleToUpdate.setArea(newRule.getArea());
        ruleToUpdate.setHas(newRule.getHas());
        ruleToUpdate.setTag(newRule.getTag());
        ruleRepository.save(ruleToUpdate);
    }

    public void deleteAllByTag(final Set<TagDAO> tagIdToDelete) {
        ruleRepository.deleteAllByUserDAOAndTagIn(UserService.getCurrentUser(), tagIdToDelete);
    }
}
