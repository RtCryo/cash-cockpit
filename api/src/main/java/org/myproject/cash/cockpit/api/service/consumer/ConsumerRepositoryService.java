package org.myproject.cash.cockpit.api.service.consumer;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.ConsumerRepository;
import org.myproject.cash.cockpit.api.repository.model.ConsumerDAO;
import org.myproject.cash.cockpit.api.rest.model.ConsumerDTO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerRepositoryService {

    private final ConsumerRepository consumerRepository;
    private final ToDAOMapper toDAOMapper;
    private final ToDTOMapper toDTOMapper;

    public List<ConsumerDTO> findAllConsumer() {
        return consumerRepository.findAllByUserDAO(UserService.getCurrentUser())
                .stream()
                .map(toDTOMapper::toConsumerDTO)
                .toList();
    }

    public ConsumerDTO save(final ConsumerDTO consumerDTO) {
        ConsumerDAO consumerDAO = toDAOMapper.toConsumerDAO(consumerDTO);
        consumerDAO.setUserDAO(UserService.getCurrentUser());
        ConsumerDAO savedConsumer = consumerRepository.save(consumerDAO);
        return toDTOMapper.toConsumerDTO(savedConsumer);
    }

    public long count() {
        return consumerRepository.countByUserDAO(UserService.getCurrentUser());
    }
}
