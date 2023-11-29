package org.myproject.cash.cockpit.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.exception.UserNotFoundException;
import org.myproject.cash.cockpit.api.repository.TokenRepository;
import org.myproject.cash.cockpit.api.repository.UserRepository;
import org.myproject.cash.cockpit.api.repository.model.Role;
import org.myproject.cash.cockpit.api.repository.model.TokenDAO;
import org.myproject.cash.cockpit.api.repository.model.TokenType;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.myproject.cash.cockpit.api.rest.model.AuthenticationResponse;
import org.myproject.cash.cockpit.api.rest.model.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO request) {
        UserDAO userDAO = UserDAO.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .enabled(true)
                .build();
        UserDAO saved = repository.save(userDAO);
        String token = jwtService.generateToken(saved);
        String refreshToken = jwtService.generateRefreshToken(saved);
        saveUserToken(saved, token);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .username(saved.getUsername())
                .build();
    }

    public AuthenticationResponse authenticate(UserDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new UserNotFoundException(request.username() + " not found!");
        }
        UserDAO userDAO = repository.findByUsername(request.username())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(userDAO);
        var refreshToken = jwtService.generateRefreshToken(userDAO);
        revokeAllUserTokens(userDAO);
        saveUserToken(userDAO, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(userDAO.getUsername())
                .build();
    }

    private void saveUserToken(UserDAO user, String jwtToken) {
        TokenDAO tokenDAO = TokenDAO.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(tokenDAO);
    }

    private void revokeAllUserTokens(UserDAO user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.repository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
