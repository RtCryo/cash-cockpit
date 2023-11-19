package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO extends AbstractDAO {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isEnabled")
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TransactionDAO> transactionDAOList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FileInfoDAO> fileInfo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RuleDAO> rule;

    @OneToMany(cascade = CascadeType.ALL)
    private List<VaultDAO> vault;
}
