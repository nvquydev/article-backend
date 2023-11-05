package com.quynv20.articlebackend.controller;

import com.quynv20.articlebackend.Utils.DTOEntityConverter;
import com.quynv20.articlebackend.dao.entity.Tag;
import com.quynv20.articlebackend.dto.TagDTO;
import com.quynv20.articlebackend.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
    public ResponseEntity<TagDTO> createTag(@Valid @RequestBody TagDTO tagDTO) {
        Tag tag = DTOEntityConverter.convertToEntity(tagDTO, Tag.class);
        Tag createdTag = tagService.createTag(tag);
        TagDTO createdTagDTO = DTOEntityConverter.convertToDTO(createdTag, TagDTO.class);
        return new ResponseEntity<>(createdTagDTO, HttpStatus.CREATED);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable String id) {
        Tag tag = tagService.getTagById(id);
        TagDTO tagDTO = DTOEntityConverter.convertToDTO(tag, TagDTO.class);
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable String id, @Valid @RequestBody TagDTO tagDTO) {
        Tag tagToUpdate = DTOEntityConverter.convertToEntity(tagDTO, Tag.class);
        Tag updatedTag = tagService.updateTag(id, tagToUpdate);
        TagDTO updatedTagDTO = DTOEntityConverter.convertToDTO(updatedTag, TagDTO.class);
        return new ResponseEntity<>(updatedTagDTO, HttpStatus.OK);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

