package org.myproject.cash.cockpit.api.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.repository.UserRepository;
import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ToDAOMapper toDAOMapper;

    public void save(final UserDTO userDTO) {
        userRepository.save(toDAOMapper.toUserDao(userDTO));
    }
}
