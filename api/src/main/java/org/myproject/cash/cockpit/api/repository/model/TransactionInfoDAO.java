package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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

}
