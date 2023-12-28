package org.myproject.cash.cockpit.messenger.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.messenger.model.ReportDto;
import org.myproject.cash.cockpit.messenger.repository.MessengerRepository;
import org.myproject.cash.cockpit.messenger.repository.UserRepository;
import org.myproject.cash.cockpit.messenger.repository.model.MessageDAO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessengerRepositoryService {

    private final MessengerRepository repository;
    private final UserRepository userRepository;

    public void createMessage(final ReportDto value) {
        if (userRepository.existsById(value.userId())) {
            MessageDAO messageDAO = MessageDAO.builder().message(value.msg()).isNew(true).userId(value.userId()).build();
            repository.save(messageDAO);
        }
    }
}
