package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@MappedSuperclass
public class AbstractDAO extends AbstractPersistable<UUID> {

    @Override
    public void setId(UUID id) {
        super.setId(id);
    }
}
