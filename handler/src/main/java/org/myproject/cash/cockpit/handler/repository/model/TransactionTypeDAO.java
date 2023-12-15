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
@Table(name = "transaction_type")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeDAO extends AbstractDAO {

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private UUID userId;
}
