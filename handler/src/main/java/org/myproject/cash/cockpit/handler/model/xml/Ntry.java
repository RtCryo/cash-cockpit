package org.myproject.cash.cockpit.handler.model.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Ntry")
public class Ntry {

    @XmlElement(name = "BookgDt")
    private BookgDt bookgDt;

    @XmlElement(name = "CdtDbtInd")
    private String cdtDbtInd;

    @XmlElement(name = "Amt")
    private double total;

    @XmlElement(name = "NtryDtls")
    private NtryDtls ntryDtls;

    @XmlElement(name = "BkTxCd")
    private BkTxCd bkTxCd;
}
