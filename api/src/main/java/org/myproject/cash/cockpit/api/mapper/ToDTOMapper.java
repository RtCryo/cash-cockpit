package org.myproject.cash.cockpit.api.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.myproject.cash.cockpit.api.repository.model.*;
import org.myproject.cash.cockpit.api.rest.model.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ToDTOMapper {

    public abstract ConsumerDTO toConsumerDTO(ConsumerDAO consumerDAO);

    @Mapping(target = "bankStatement", source = "bankStatement.id")
    public abstract FileInfoDTO toFileInfoDTO(FileInfoDAO fileInfoDAO);

    public abstract FileDTO toFileDTO(FileDAO fileDAO);

    @Mapping(target = "tagId", source = "tag.id")
    public abstract RuleDTO toRuleDTO(RuleDAO ruleDAO);

    public abstract TagDTO toTagDTO(TagDAO tagDAO);

    protected String hasToString(final HasDAO ruleHasJpa) {
        return ruleHasJpa.getHas();
    }

}
