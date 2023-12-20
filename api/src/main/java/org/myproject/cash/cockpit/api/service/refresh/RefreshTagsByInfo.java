package org.myproject.cash.cockpit.api.service.refresh;

import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.springframework.stereotype.Service;

@Service
public class RefreshTagsByInfo implements RefreshTagsByArea {

    public String getAreaType() {
        return "INFO";
    }

    public String refresh(final TransactionDAO transactionJpas) {
        return transactionJpas.getTransactionInfo().getInfo();
    }


}
