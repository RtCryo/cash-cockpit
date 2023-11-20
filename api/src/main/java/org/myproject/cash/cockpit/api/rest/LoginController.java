package org.myproject.cash.cockpit.api.rest;

import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.myproject.cash.cockpit.api.service.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<Object> getLoginPage(@RequestBody UserDTO request) {
        try {
            String token = jwtTokenService.createToken(request.username(), "user");
            Map<Object, Object> response = new HashMap<>();
            response.put("username", request.username());
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

}
