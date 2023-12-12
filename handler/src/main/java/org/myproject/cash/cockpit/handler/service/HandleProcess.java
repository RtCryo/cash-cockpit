package org.myproject.cash.cockpit.handler.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.handler.consumer.FileInfoFacade;
import org.myproject.cash.cockpit.handler.exception.HandlerException;
import org.myproject.cash.cockpit.handler.model.Transaction;
import org.myproject.cash.cockpit.handler.repository.model.FileInfoDAO;
import org.myproject.cash.cockpit.handler.repository.model.ProgressStatus;
import org.myproject.cash.cockpit.handler.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.handler.service.parser.TransactionParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandleProcess {

    private final TransactionParser parser;
    private final FileInfoRepositoryService fileInfoRepositoryService;
    private final TransactionRepositoryService transactionRepositoryService;
    private final MessengerService messengerService;

    @Transactional
    public void run(final FileInfoFacade infoFacade) {
        try {
            FileInfoDAO fileInfoDAO = fileInfoRepositoryService.findFileInfoById(infoFacade.getFileInfoId());
            List<Transaction> transactions = parser.getTransactionListFromByte(infoFacade.getFile());
            List<TransactionDAO> saved = transactionRepositoryService
                    .saveTransactions(transactions, fileInfoDAO.getUserId(), parser.getLocalDateTransactionStart(), parser.getLocalDateTransactionEnd());
            fileInfoRepositoryService.updateAndSaveFileInfo(fileInfoDAO, parser.getLocalDateTransactionStart(), parser.getLocalDateTransactionEnd());
            messengerService.sendReport(infoFacade.getUserId(), infoFacade.getFileInfoId(), getSuccessMsg(fileInfoDAO.getName(), transactions.size(), saved.size()), true);
        } catch (final HandlerException ex) {
            if (fileInfoRepositoryService.isExist(infoFacade.getFileInfoId())) {
                FileInfoDAO fileInfoDAO = fileInfoRepositoryService.findFileInfoById(infoFacade.getFileInfoId());
                fileInfoDAO.setStatus(ProgressStatus.ERROR);
                fileInfoRepositoryService.save(fileInfoDAO);
            }
            messengerService.sendReport(infoFacade.getUserId(), infoFacade.getFileInfoId(), ex.getMessage(), false);
        }
    }

    private String getSuccessMsg(final String fileName, final int transactionCount, final int transactionSaved) {
        return "File: " + fileName + " succesfully transformed. Found: " + transactionCount + " transactions and saved: " + transactionSaved;
    }

}
