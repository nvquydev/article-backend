package com.quynv20.articlebackend.dao.repository;

import com.quynv20.articlebackend.dao.entity.Article;
import com.quynv20.articlebackend.dao.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
