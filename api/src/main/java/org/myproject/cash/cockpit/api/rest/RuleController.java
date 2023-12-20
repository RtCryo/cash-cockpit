package org.myproject.cash.cockpit.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.rest.model.RuleDTO;
import org.myproject.cash.cockpit.api.service.refresh.RefreshTagsByRuleService;
import org.myproject.cash.cockpit.api.service.rule.RuleRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/rule")
@RequiredArgsConstructor
public class RuleController {

    private final RuleRepositoryService ruleRepositoryService;
    private final RefreshTagsByRuleService refreshTagsByRuleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RuleDTO> getRules() {
        return ruleRepositoryService.getAllRules();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO saveRule(final @Valid @RequestBody RuleDTO ruleDto) {
        ruleRepositoryService.createRule(ruleDto);
        return MessageDTO.create(0, "Rule successful created");
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO delete(@RequestBody final List<UUID> ruleIds) {
        ruleRepositoryService.deleteAllRule(ruleIds);
        return MessageDTO.create(0, "Rule(s) successful deleted");
    }

    @PostMapping("/{ruleId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO updateRule(@PathVariable final UUID ruleId, final @Valid @RequestBody RuleDTO ruleDto) {
        ruleRepositoryService.update(ruleDto, ruleId);
        return MessageDTO.create(0, "Rule successful updated");
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO updateRule(@RequestBody final Set<UUID> ruleIds) {
        refreshTagsByRuleService.refreshAndSaveTransaction(ruleIds);
        return MessageDTO.builder().code(0).message("Rule(s) successful refreshed").build();
    }


}
