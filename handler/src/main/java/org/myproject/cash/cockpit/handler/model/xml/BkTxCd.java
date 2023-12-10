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
@XmlRootElement(name = "BkTxCd")
public class BkTxCd {

    @XmlElement(name = "Domn")
    private Domn domn;

}
