package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
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

}
