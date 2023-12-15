package org.myproject.cash.cockpit.handler.service.enricher.tag;

import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;

public interface RefreshTagsByArea {

    String getAreaType();

    String refresh(TransactionDAO transactionJpas);

}
