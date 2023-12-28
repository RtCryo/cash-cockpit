package org.myproject.cash.cockpit.api.service.transaction;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.DatepickerNullException;
import org.myproject.cash.cockpit.api.exception.TransactionNotFoundException;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.TransactionRepository;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionTypeDAO;
import org.myproject.cash.cockpit.api.rest.model.TransactionDTO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryService {

    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepositoryService transactionTypeRepositoryService;
    private final ToDTOMapper toDTOMapper;
    private final ToDAOMapper toDAOMapper;

    private static final String CUSTOM = "CUSTOM";

    public long count() {
        return transactionRepository.countByUserDAO(UserService.getCurrentUser());
    }

    public List<TransactionDAO> findAllByTagId(final Iterable<TagDAO> tagIdToDelete) {
        return transactionRepository.findAllByTagsIn(tagIdToDelete);
    }

    public void saveAll(final List<TransactionDAO> updatedList) {
        transactionRepository.saveAll(updatedList);
    }

    public List<TransactionDTO> findTransactionsWithFilter(final LocalDate localDateStart, final LocalDate localDateEnd, final List<UUID> tags) {
        if (tags.isEmpty()) {
            return this.findTransactionsWithDateFilter(localDateStart, localDateEnd);
        }
        return transactionRepository
                .findByDateBetweenAndTagsOrderByDate(localDateStart, localDateEnd, tags, tags.size(), UserService.getCurrentUser().getId()).stream()
                .map(toDTOMapper::toTransactionDTO)
                .toList();
    }

    public List<TransactionDTO> findTransactionsWithDateFilter(final LocalDate start, final LocalDate end) {
        validateDatepicker(start, end);
        return transactionRepository.findAllByUserDAOAndTransactionDateBetweenOrderByTransactionDate(UserService.getCurrentUser(), start, end)
                .stream()
                .map(toDTOMapper::toTransactionDTO)
                .toList();
    }

    private void validateDatepicker(final LocalDate start, final LocalDate end) {
        if (isNull(start) || isNull(end)) {
            throw new DatepickerNullException("Date range is wrong");
        }
    }

    public TransactionDTO findTransaction(final UUID id) {
        return transactionRepository.findById(id)
                .map(toDTOMapper::toTransactionDTO)
                .orElseThrow(TransactionNotFoundException::new);
    }

    public void updateTransaction(final UUID id, final TransactionDTO transaction) {
        TransactionDAO dao = transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
        dao.toBuilder()
                .tags(toDAOMapper.toListTagDAO(transaction.tags()))
                .transactionInfo(toDAOMapper.toInfoDAO(transaction.transactionInfo()))
                .build();
        transactionRepository.save(dao);
    }

    public TransactionDTO saveCustomTransaction(final TransactionDTO transaction) {
        TransactionDAO customTransaction = toDAOMapper.toTransactionDAO(transaction);
        customTransaction.setTransactionType(getCustomTransactionType());
        TransactionDAO saved = transactionRepository.save(customTransaction);
        return toDTOMapper.toTransactionDTO(saved);
    }

    private TransactionTypeDAO getCustomTransactionType() {
        return transactionTypeRepositoryService.findByType(CUSTOM);
    }

    public void removeCustomTransaction(final UUID id) {
        validateCustomTransaction(id);
        transactionRepository.deleteById(id);
    }

    private void validateCustomTransaction(UUID id) {
        TransactionTypeDAO custom = transactionTypeRepositoryService.findByType(CUSTOM);
        TransactionDAO transactionDAO = transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
        if (!Objects.equals(custom.getId(), transactionDAO.getTransactionType().getId())) {
            throw new IllegalArgumentException("CUSTOM type not found");
        }
    }

    public List<TransactionDAO> findAll() {
        return transactionRepository.findAllByUserDAO(UserService.getCurrentUser());
    }
}
