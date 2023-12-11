package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;
}
