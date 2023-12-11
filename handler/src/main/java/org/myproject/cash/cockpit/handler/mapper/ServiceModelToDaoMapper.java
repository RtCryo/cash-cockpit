package org.myproject.cash.cockpit.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.model.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceModelToDaoMapper {

    @Mapping(target = "consumer", source = "destination")
    TransactionDAO toDao(Transaction transaction);

}
