package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "token")
@NoArgsConstructor
@AllArgsConstructor
public class TokenDAO extends AbstractDAO {

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDAO user;

}
