package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "rule")
@NoArgsConstructor
@AllArgsConstructor
public class RuleDAO extends AbstractDAO {

    @OneToMany(cascade = CascadeType.ALL)
    private List<RuleHasDAO> has;

    @Enumerated(EnumType.STRING)
    private RuleArea area;

    @ManyToOne
    @JoinColumn(name = "tag_ID")
    private TagDAO tag;

}
