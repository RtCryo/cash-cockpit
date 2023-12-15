package org.myproject.cash.cockpit.handler.service.enricher.tag;

import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

@Service
public class RefreshTagsBySender implements RefreshTagsByArea {

    public String getAreaType() {
        return "SENDER";
    }

    public String refresh(final TransactionDAO transactionJpas) {
        return transactionJpas.getSender().getName();
    }

}
