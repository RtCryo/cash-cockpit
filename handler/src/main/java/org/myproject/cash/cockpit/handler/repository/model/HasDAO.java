package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "has")
@NoArgsConstructor
@AllArgsConstructor
public class HasDAO extends AbstractDAO {

    private String has;
}
