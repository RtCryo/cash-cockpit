package org.myproject.cash.cockpit.api.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.myproject.cash.cockpit.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO usersPostCreateController(@RequestBody @Valid UserDTO userDTO) {
        userService.save(userDTO);
        return MessageDTO.create(0, "Hallo new User - " + userDTO.username());
    }

}
