package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@Table(name = "vault")
@NoArgsConstructor
@AllArgsConstructor
public class VaultDAO extends AbstractDAO {

    @Builder.Default
    private LocalDate date = LocalDate.now();
    private double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;

}
