package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany
    @JoinTable(name = "rule_tag",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<RuleDAO> rule;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;
}
