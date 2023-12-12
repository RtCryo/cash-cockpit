package org.myproject.cash.cockpit.messenger.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.messenger.model.ReportDto;
import org.myproject.cash.cockpit.messenger.repository.MessengerRepository;
import org.myproject.cash.cockpit.messenger.repository.model.MessageDAO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessengerRepositoryService {

    private final MessengerRepository repository;

    public void createMessage(final ReportDto value) {
        MessageDAO messageDAO = MessageDAO.builder().message(value.msg()).isNew(true).userId(value.userId()).build();
        repository.save(messageDAO);
    }
}
