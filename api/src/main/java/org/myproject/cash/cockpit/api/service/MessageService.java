package org.myproject.cash.cockpit.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.MessageRepository;
import org.myproject.cash.cockpit.api.repository.model.MessageDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public List<String> getNewMessage() {
        List<MessageDAO> newMessage = messageRepository.findAllByUserAndIsNew(UserService.getCurrentUser(), true);
        newMessage.forEach(messageDAO -> messageDAO.setIsNew(false));
        messageRepository.saveAll(newMessage);
        return newMessage.stream().map(MessageDAO::getMessage).toList();
    }

}
