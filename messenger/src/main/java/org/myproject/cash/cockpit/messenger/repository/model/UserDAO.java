package org.myproject.cash.cockpit.messenger.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    @Id
    @GeneratedValue
    @Nullable
    private UUID id;

}
