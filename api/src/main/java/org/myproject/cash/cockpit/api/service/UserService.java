package org.myproject.cash.cockpit.api.service;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.UserIsExistException;
import org.myproject.cash.cockpit.api.mapper.ToDAOMapper;
import org.myproject.cash.cockpit.api.repository.UserRepository;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ToDAOMapper toDAOMapper;

    public void save(final UserDTO userDTO) {
        validateUsername(userDTO.username());
        userRepository.save(toDAOMapper.toUserDao(userDTO));
    }

    private void validateUsername(final String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserIsExistException(username + " is exist");
        }
    }

    public static UserDAO getCurrentUser() {
        return (UserDAO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
