package org.myproject.cash.cockpit.api.service.consumer;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.ConsumerRepository;
import org.myproject.cash.cockpit.api.repository.model.ConsumerDAO;
import org.myproject.cash.cockpit.api.rest.model.ConsumerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerRepositoryService {

    private final ConsumerRepository consumerRepository;
    private final ToDAOMapper toDAOMapper;
    private final ToDTOMapper toDTOMapper;

    public List<ConsumerDTO> findAllConsumer() {
        return consumerRepository.findAll().stream().map(toDTOMapper::toConsumerDTO).toList();
    }

    public ConsumerDTO save(final ConsumerDTO consumerDTO) {
        ConsumerDAO savedConsumer = consumerRepository.save(toDAOMapper.toConsumerDAO(consumerDTO));
        return toDTOMapper.toConsumerDTO(savedConsumer);
    }

    public long count() {
        return consumerRepository.count();
    }
}
