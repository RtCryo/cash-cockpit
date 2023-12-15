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
@Table(name = "transaction_info")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfoDAO extends AbstractDAO {

    @Column(name = "info", columnDefinition = "TEXT")
    private String info;

    @Column(name = "user_id")
    private UUID userId;

}
