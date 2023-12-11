package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "sender")
@NoArgsConstructor
@AllArgsConstructor
public class SenderDAO extends AbstractDAO {

    @Column(name = "sender_name", nullable = false)
    private String name;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;
}
