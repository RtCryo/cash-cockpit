package org.myproject.cash.cockpit.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.myproject.cash.cockpit.api.repository.model.*;
import org.myproject.cash.cockpit.api.rest.model.ConsumerDTO;
import org.myproject.cash.cockpit.api.rest.model.FileDTO;
import org.myproject.cash.cockpit.api.rest.model.FileInfoDTO;
import org.myproject.cash.cockpit.api.rest.model.RuleDTO;

@Mapper(componentModel = "spring")
public abstract class ToDTOMapper {

    public abstract ConsumerDTO toConsumerDTO(ConsumerDAO consumerDAO);

    public abstract FileInfoDTO toFileInfoDTO(FileInfoDAO fileInfoDAO);

    public abstract FileDTO toFileDTO(FileDAO fileDAO);

    @Mapping(target = "tagId", source = "tag.id")
    public abstract RuleDTO toRuleDTO(RuleDAO ruleDAO);

    protected String hasToString(final RuleHasDAO ruleHasJpa) {
        return ruleHasJpa.getHas();
    }

}
