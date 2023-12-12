package org.myproject.cash.cockpit.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.myproject.cash.cockpit.handler.model.*;
import org.myproject.cash.cockpit.handler.repository.model.*;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DaoToServiceModelMapper {

    public abstract Tag toTag(TagDAO tagDAO);

    public abstract Destination toConsumer(ConsumerDAO consumerDAO);

    public abstract TransactionInfo toTransactionInfo(TransactionInfoDAO transactionInfoJpa);

    public abstract Transaction toTransaction(TransactionDAO transactionJpa);

    public abstract TransactionType toTransactionType(TransactionTypeDAO transactionTypeJpa);

    public abstract File toFile(FileDAO fileDAO);

    @Mapping(source = "bankStatement", target = "bankStatementId")
    public abstract FileInfo toFileInfo(FileInfoDAO fileInfoDAO);

    protected UUID fileToId(FileDAO file) {
        return file.getId();
    }

}
