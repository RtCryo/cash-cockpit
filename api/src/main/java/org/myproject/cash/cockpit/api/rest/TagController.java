package org.myproject.cash.cockpit.api.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.rest.model.MessageDTO;
import org.myproject.cash.cockpit.api.rest.model.TagDTO;
import org.myproject.cash.cockpit.api.service.tag.TagRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagRepositoryService tagRepositoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> showAllTags() {
        return tagRepositoryService.findAllTags();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO createTag(@RequestBody @NotNull @NotBlank final String newTagName) {
        tagRepositoryService.createTag(newTagName);
        return MessageDTO.create(0, "Tag successful created");
    }

    @PostMapping("/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO updateTag(@PathVariable final String tagId, @RequestBody final TagDTO updateTag) {
        tagRepositoryService.updateTag(tagId, updateTag);
        return MessageDTO.create(0, "Tag successful updated");
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO deleteTag(@RequestBody final Set<TagDTO> tagToDelete) {
        tagRepositoryService.deleteTags(tagToDelete);
        return MessageDTO.create(0, "deleted");
    }

}
