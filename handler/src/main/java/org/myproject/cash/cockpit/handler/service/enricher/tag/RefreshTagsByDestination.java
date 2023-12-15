package org.myproject.cash.cockpit.handler.service.enricher.tag;

import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

@Service
public class RefreshTagsByDestination implements RefreshTagsByArea {

    public String getAreaType() {
        return "CONSUMER";
    }

    public String refresh(final TransactionDAO transactionJpas) {
        return transactionJpas.getConsumer().getName();
    }

}
