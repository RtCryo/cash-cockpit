package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


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

}
