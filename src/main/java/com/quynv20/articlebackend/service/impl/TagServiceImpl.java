package com.quynv20.articlebackend.service.impl;

import com.quynv20.articlebackend.dao.entity.Tag;
import com.quynv20.articlebackend.dao.repository.TagRepository;
import com.quynv20.articlebackend.exception.ResourceNotFoundException;
import com.quynv20.articlebackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(String id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public Tag createTag(Tag tag) {
        // Thêm logic kiểm tra và xử lý dữ liệu nếu cần
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(String id, Tag updatedTag) {
        Tag tag = getTagById(id);

        // Cập nhật thông tin tag với dữ liệu từ updatedTag
        tag.setName(updatedTag.getName());

        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(String id) {
        Tag tag = getTagById(id);
        tagRepository.delete(tag);
    }

}
