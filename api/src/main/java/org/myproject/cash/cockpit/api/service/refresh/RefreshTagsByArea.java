package org.myproject.cash.cockpit.api.service.refresh;


import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;

public interface RefreshTagsByArea {

    String getAreaType();

    String refresh(TransactionDAO transactionJpas);

}
