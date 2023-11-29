package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;

}
