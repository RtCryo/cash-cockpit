package org.myproject.cash.cockpit.handler.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class PaymentType {

    private final Map<String, String> typeMap = new HashMap<>();

    public PaymentType() {
        typeMap.put("CCRD/OTHR", "KARTENZAHLUNG");
        typeMap.put("RCDT/ESCT", "GUTSCHRIFT");
        typeMap.put("RDDT/ESDD", "LASTSCHRIFT");
        typeMap.put("CNTR/CWDL", "BARGELDAUSZAHLUNG");
        typeMap.put("ICDT/STDO", "DAUERAUFTRAG");
        typeMap.put("ICDT/ESCT", "UEBERWEISUNG");
        typeMap.put("OPCL/ACCC", "ZINSEN/ENTGELD");
        typeMap.put("RCDT/SALA", "GEHALT/RENTE");
        typeMap.put("RDDT/UPDD", "STORNO");
        typeMap.put("MCOP/OTHR", "BEZÜGE");
        typeMap.put("CNTR/CDPT", "EINZAHLUNG");
        typeMap.put("ICDT/XBST", "DAUERAUFTRAG");
    }

}
