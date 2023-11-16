package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO extends AbstractDAO{

    private String username;
    private String password;
    private boolean enabled;

}
