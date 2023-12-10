package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class FileDAO extends AbstractDAO {

    @Lob
    @Column
    private byte[] fileByte;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO userDAO;

}
