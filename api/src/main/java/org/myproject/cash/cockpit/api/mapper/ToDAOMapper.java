package org.myproject.cash.cockpit.api.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.myproject.cash.cockpit.api.repository.model.*;
import org.myproject.cash.cockpit.api.rest.model.ConsumerDTO;
import org.myproject.cash.cockpit.api.rest.model.RuleDTO;
import org.myproject.cash.cockpit.api.rest.model.TagDTO;
import org.myproject.cash.cockpit.api.rest.model.TransactionDTO;
import org.myproject.cash.cockpit.api.service.tag.TagRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ToDAOMapper {

    @Autowired
    private TagRepositoryService tagRepositoryService;

    public abstract ConsumerDAO toConsumerDAO(ConsumerDTO consumerDTO);

    @Mapping(target = "tag", source = "tagId")
    public abstract RuleDAO toRuleDAO(RuleDTO ruleDto);

    public abstract TagDAO toTagDAO(TagDTO tagDTO);

    public abstract TransactionDAO toTransactionDAO(TransactionDTO transactionDTO);

    protected HasDAO toRuleHas(final String has) {
        return HasDAO.builder()
                .has(has)
                .build();
    }

    protected TagDAO toTag(final UUID id) {
        return tagRepositoryService.findById(id);
    }

}
