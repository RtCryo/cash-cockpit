package org.myproject.cash.cockpit.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.myproject.cash.cockpit.handler.model.Tag;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.TransactionInfo;
import org.myproject.cash.cockpit.handler.model.TransactionType;
import org.myproject.cash.cockpit.handler.repository.model.*;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DaoToServiceModelMapper {

    public abstract Tag toTag(TagDAO tagDAO);

    public abstract TransactionInfo toTransactionInfo(TransactionInfoDAO transactionInfoJpa);

    public abstract Transaction toTransaction(TransactionDAO transactionJpa);

    public abstract TransactionType toTransactionType(TransactionTypeDAO transactionTypeJpa);

    protected UUID fileToId(FileDAO file) {
        return file.getId();
    }

}
