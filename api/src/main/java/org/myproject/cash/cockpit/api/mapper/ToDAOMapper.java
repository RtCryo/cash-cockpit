package org.myproject.cash.cockpit.api.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.myproject.cash.cockpit.api.exception.TagNotFoundException;
import org.myproject.cash.cockpit.api.repository.TagRepository;
import org.myproject.cash.cockpit.api.repository.model.*;
import org.myproject.cash.cockpit.api.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ToDAOMapper {

    @Autowired
    private TagRepository tagRepository;

    public abstract ConsumerDAO toConsumerDAO(ConsumerDTO consumerDTO);

    @Mapping(target = "tag", source = "tagId")
    public abstract RuleDAO toRuleDAO(RuleDTO ruleDto);

    public abstract Set<TagDAO> toListTagDAO(List<TagDTO> tagDTO);

    public abstract TransactionDAO toTransactionDAO(TransactionDTO transactionDTO);

    public abstract TransactionInfoDAO toInfoDAO(TransactionInfoDTO dto);

    @Mapping(target = "enabled", constant = "true")
    public abstract UserDAO toUserDao(UserDTO userDTO);

    protected HasDAO toRuleHas(final String has) {
        return HasDAO.builder()
                .has(has)
                .build();
    }

    protected TagDAO toTag(final UUID id) {
        return tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
    }

}
