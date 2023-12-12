package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "consumer")
@NoArgsConstructor
@AllArgsConstructor
public final class ConsumerDAO extends AbstractDAO {

    @Column(nullable = false)
    private String name;

    @Column(name = "user_id")
    private UUID userId;
}
