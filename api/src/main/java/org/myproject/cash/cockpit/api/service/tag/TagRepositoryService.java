package org.myproject.cash.cockpit.api.service.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myproject.cash.cockpit.api.exception.CreateNewTagException;
import org.myproject.cash.cockpit.api.exception.TagNotFoundException;
import org.myproject.cash.cockpit.api.mapper.ToDTOMapper;
import org.myproject.cash.cockpit.api.repository.TagRepository;
import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.api.rest.model.TagDTO;
import org.myproject.cash.cockpit.api.service.rule.RuleRepositoryService;
import org.myproject.cash.cockpit.api.service.transaction.TransactionRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagRepositoryService {

    private final TransactionRepositoryService transactionRepositoryService;
    private final RuleRepositoryService ruleRepositoryService;
    private final TagRepository tagRepository;
    private final ToDTOMapper mapper;

    public long count() {
        return tagRepository.count();
    }

    public TagDAO findById(final UUID id) {
        return tagRepository.findById(id).orElseThrow(() -> {
            log.error("Tag id: [" + id + "] not found");
            return new TagNotFoundException();
        });
    }

    public List<TagDTO> findAllTags() {
        return tagRepository.findAll().stream().map(mapper::toTagDTO).toList();
    }

    public void createTag(final String newTagName) {
        validateTag(newTagName);
        TagDAO tagDAO = TagDAO.builder().tagName(newTagName).build();
        tagRepository.save(tagDAO);
    }

    public void updateTag(final String tagId, final TagDTO updateTag) {
        validateTag(updateTag.tagName());
        TagDAO tagDAO = tagRepository.findById(UUID.fromString(tagId)).orElseThrow(TagNotFoundException::new);
        tagDAO.setTagName(updateTag.tagName());
        tagRepository.save(tagDAO);
    }

    private void validateTag(final String newTagName) {
        if (tagRepository.existsByTagName(newTagName)) {
            throw new CreateNewTagException(newTagName);
        }
    }

    public void deleteTags(final Set<TagDTO> tagToDelete) {
        Set<UUID> tagIdToDelete = tagToDelete.stream().map(TagDTO::id).collect(Collectors.toSet());
        Set<TagDAO> tagDaoToDelete = new HashSet<>(tagRepository.findAllById(tagIdToDelete));
        removeTagFromTransactions(tagDaoToDelete);
        removeRulesByTag(tagDaoToDelete);

    }

    private void removeRulesByTag(final Set<TagDAO> tagIdToDelete) {
        ruleRepositoryService.deleteAllByTag(tagIdToDelete);
    }

    private void removeTagFromTransactions(final Set<TagDAO> tagDaoToDelete) {
        List<TransactionDAO> daos = transactionRepositoryService.findAllByTagId(tagDaoToDelete);
        daos.forEach(transactionDAO -> transactionDAO.getTags().removeAll(tagDaoToDelete));
        transactionRepositoryService.saveAll(daos);
    }
}
