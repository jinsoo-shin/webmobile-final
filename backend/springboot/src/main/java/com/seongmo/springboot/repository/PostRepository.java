package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p ORDER BY create_at")
    List<Post> getAllPost();
}
