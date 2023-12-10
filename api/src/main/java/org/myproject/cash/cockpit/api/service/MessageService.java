package org.myproject.cash.cockpit.api.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;


    public List<String> getAllMessage() {
        UserDAO userDAO = userService.getUser();

        return null;
    }

}
