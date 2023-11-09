package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}
