package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "rule")
@NoArgsConstructor
@AllArgsConstructor
public class RuleDAO extends AbstractDAO {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rule_has",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "has_id"))
    private List<HasDAO> has;

    @Enumerated(EnumType.STRING)
    private RuleArea area;

    @OneToOne
    @JoinColumn(name = "tag_id")
    private TagDAO tag;

    @Column(name = "user_id")
    private UUID userId;
}