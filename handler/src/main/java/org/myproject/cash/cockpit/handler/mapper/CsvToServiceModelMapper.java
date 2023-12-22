package org.myproject.cash.cockpit.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.csv.CsvTransaction;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CsvToServiceModelMapper {

    @Mapping(target = "transactionDate", source = "bookingDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "transactionInfo.info", source = "purpose")
    @Mapping(target = "transactionType.type", source = "transactionType")
    @Mapping(target = "destination.name", source = "beneficiary")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "total", expression = "java(convertSum(csvTransaction.getAmount()))")
    Transaction toTransaction(CsvTransaction csvTransaction);

    default double convertSum(String amount) {
        return Double.parseDouble(amount.replace(',', '.'));
    }

}
