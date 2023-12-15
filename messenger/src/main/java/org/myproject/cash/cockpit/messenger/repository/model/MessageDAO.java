package org.myproject.cash.cockpit.messenger.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class MessageDAO {

    @Id
    @GeneratedValue
    @Nullable
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    private String message;
    private Boolean isNew;

}
