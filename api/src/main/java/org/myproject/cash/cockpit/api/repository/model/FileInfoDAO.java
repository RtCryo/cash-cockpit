package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@Table(name = "file_info")
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDAO extends AbstractDAO {

    @Column(name = "name", nullable = false)
    private String name;

    private String type;

    @Column(name = "start_date")
    private LocalDate start;

    @Column(name = "end_date")
    private LocalDate end;

    @OneToOne(cascade = CascadeType.ALL)
    private FileDAO bankStatement;

}
