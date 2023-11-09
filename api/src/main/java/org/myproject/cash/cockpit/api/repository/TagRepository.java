package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TagRepository extends ListCrudRepository<TagDAO, UUID> {

    boolean existsByTagName(String tagName);

}
