package com.seongmo.springboot.repository;

import com.seongmo.springboot.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findByBno(Long bno);
}
