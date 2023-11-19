package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    private UserDAO userDAO;

}
