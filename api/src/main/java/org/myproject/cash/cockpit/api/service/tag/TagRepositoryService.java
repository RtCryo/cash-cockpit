package org.myproject.cash.cockpit.api.service.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myproject.cash.cockpit.api.exception.TagNotFoundException;
import org.myproject.cash.cockpit.api.repository.TagRepository;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagRepositoryService {

    private final TagRepository tagRepository;

    public long count() {
        return tagRepository.count();
    }

    public TagDAO findById(final UUID id) {
        return tagRepository.findById(id).orElseThrow(() -> {
            log.error("Tag id: [" + id + "] not found");
            return new TagNotFoundException();
        });
    }
}
