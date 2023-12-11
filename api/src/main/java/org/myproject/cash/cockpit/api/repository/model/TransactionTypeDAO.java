package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;
}
