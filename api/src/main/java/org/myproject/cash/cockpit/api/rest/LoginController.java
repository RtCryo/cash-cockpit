package org.myproject.cash.cockpit.api.rest;

import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/login")
public class LoginController {


    @GetMapping
    public ResponseEntity<UserDTO> getLoginPage(@AuthenticationPrincipal final User user) {
        if (isNull(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(UserDTO.builder().username(user.getUsername()).build(), HttpStatus.OK);
    }

}
