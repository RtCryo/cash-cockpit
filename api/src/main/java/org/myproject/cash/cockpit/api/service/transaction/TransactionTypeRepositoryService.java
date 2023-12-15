package org.myproject.cash.cockpit.api.service.transaction;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.TransactionTypeRepository;
import org.myproject.cash.cockpit.api.repository.model.TransactionTypeDAO;
import org.myproject.cash.cockpit.api.rest.model.TransactionTypeDTO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionTypeRepositoryService {

    private final TransactionTypeRepository transactionTypeRepository;
    private final ToDTOMapper toDTOMapper;

    public TransactionTypeDAO findByType(final String type) {
        return transactionTypeRepository.findByUserDAOAndType(UserService.getCurrentUser(), type)
                .orElseGet(this::createCustomType);
    }

    private TransactionTypeDAO createCustomType() {
        return transactionTypeRepository.save(
                TransactionTypeDAO.builder()
                        .userDAO(UserService.getCurrentUser())
                        .type("CUSTOM")
                        .build());
    }

    public List<TransactionTypeDTO> findAllTransactionTypes() {
        return transactionTypeRepository.findAllByUserDAO(UserService.getCurrentUser())
                .stream()
                .map(toDTOMapper::toTypeDTO)
                .toList();
    }
}
