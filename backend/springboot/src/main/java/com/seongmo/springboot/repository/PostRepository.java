package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Portfolio;
import com.seongmo.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByAuthor(String author);
    @Transactional
    void deleteByAuthor(String author);
}
