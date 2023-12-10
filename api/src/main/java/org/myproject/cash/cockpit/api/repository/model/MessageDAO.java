package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class MessageDAO extends AbstractDAO {

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO user;
    private String message;
    private Boolean isNew;

}
