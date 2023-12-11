package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
public class TagDAO extends AbstractDAO {

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "tags", cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<TransactionDAO> transactions;

    @Column(name = "user_id")
    private UUID userId;
}
