package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class MessageDAO extends AbstractDAO {

    private String message;
    private Boolean isNew;

    @Column(name = "user_id")
    private UUID userId;
}
