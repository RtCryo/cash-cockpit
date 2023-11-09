package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "ruleHas")
@NoArgsConstructor
@AllArgsConstructor
public class RuleHasDAO extends AbstractDAO {

    private String has;
}
