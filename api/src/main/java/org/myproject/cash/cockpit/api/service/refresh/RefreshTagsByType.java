package org.myproject.cash.cockpit.api.service.refresh;

import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

@Service
public class RefreshTagsByType implements RefreshTagsByArea {

    public String getAreaType() {
        return "TYPE";
    }

    public String refresh(final TransactionDAO transactionJpas) {
        return transactionJpas.getTransactionType().getType();
    }

}
