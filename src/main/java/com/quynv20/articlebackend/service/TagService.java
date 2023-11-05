package com.quynv20.articlebackend.service;

import com.quynv20.articlebackend.dao.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag getTagById(String id);
    Tag createTag(Tag tag);
    Tag updateTag(String id, Tag tag);
    void deleteTag(String id);
}

