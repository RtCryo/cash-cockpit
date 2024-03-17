package org.myproject.cash.cockpit.handler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.myproject.cash.cockpit.handler.config.PaymentType;
import org.myproject.cash.cockpit.handler.exception.PriceTypeIsUnknown;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.model.TransactionInfo;
import org.myproject.cash.cockpit.handler.model.TransactionType;
import org.myproject.cash.cockpit.handler.model.xml.Fmly;
import org.myproject.cash.cockpit.handler.model.xml.Ntry;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class XmlToServiceModelMapper {

    private final PaymentType paymentTypes = new PaymentType();

    @Mapping(target = "transactionDate", source = "bookgDt.date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "transactionInfo", source = "ntryDtls.txDtls.rmtInf.infoList")
    @Mapping(target = "transactionType", source = ".")
    @Mapping(target = "destination.name", source = "ntryDtls.txDtls.rltdPties.cdtr.destination", defaultValue = "Not provided")
    @Mapping(target = "total", expression = "java(setMinusWhenDBIT(ntry))")
    @Mapping(target = "tags", ignore = true)
    public abstract Transaction toTransactionFromXml(Ntry ntry);

    protected TransactionInfo listToInfo(final List<String> infoList) {
        if (infoList == null) {
            return TransactionInfo.builder()
                    .info("EMPTY")
                    .build();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String info : infoList) {
            stringBuilder.append(info).append(" ");
        }
        return TransactionInfo.builder()
                .info(stringBuilder.toString().trim())
                .build();
    }

    protected TransactionType toTransactionType(final Ntry ntry) {
        Fmly fmly;
        try {
            fmly = ntry.getBkTxCd().getDomn().getFmly();
        } catch (Exception e) {
            if (ntry.getNtryDtls().getTxDtls().getRmtInf().getInfoList().stream()
                    .anyMatch(s -> s.replaceAll("\\s", "").toUpperCase().contains("entgelt".toUpperCase()))) {
                return TransactionType.builder()
                        .type("ZINSEN/ENTGELD")
                        .build();
            }
            return TransactionType.builder()
                    .type("UNKNOWN TYPE")
                    .build();
        }
        if (fmly == null) {
            return TransactionType.builder()
                    .type("UNKNOWN TYPE")
                    .build();
        }
        String paymentType = fmly.getPaymentType() + "/" + fmly.getPaymentAnotherType();
        return TransactionType.builder()
                .type(paymentTypes.getTypeMap().get(paymentType))
                .build();
    }

    protected double setMinusWhenDBIT(final Ntry ntry) {
        if (ntry.getCdtDbtInd().equalsIgnoreCase("DBIT")) {
            return ntry.getTotal() * -1;
        } else if (ntry.getCdtDbtInd().equalsIgnoreCase("CRDT")) {
            return ntry.getTotal();
        }
        throw new PriceTypeIsUnknown(ntry.getCdtDbtInd() + ": unknown price type");
    }

}
