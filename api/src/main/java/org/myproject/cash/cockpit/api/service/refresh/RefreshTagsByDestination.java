package org.myproject.cash.cockpit.api.service.refresh;

import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
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
