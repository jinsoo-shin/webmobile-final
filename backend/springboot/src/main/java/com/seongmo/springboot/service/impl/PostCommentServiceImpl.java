package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.PostComment;
import com.seongmo.springboot.repository.PostCommentRepository;
import com.seongmo.springboot.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Autowired
    PostCommentRepository postCommentRepository;

    @Override
    public void insertPostComment(PostComment postComment) throws Exception {
        postComment.setCreate_at(LocalDate.now());
        postCommentRepository.save(postComment);
    }

    @Override
    public List<PostComment> getAllPostComment(Long bno) throws Exception {
        return postCommentRepository.findByBno(bno);
    }

    @Override
    public PostComment getPostComment(Long cno) throws Exception {
        return postCommentRepository.findById(cno).get();
    }

    @Override
    public void updatePostComment(PostComment comment) throws Exception {
        PostComment p = postCommentRepository.findById(comment.getCno()).get();
        p.setContent(comment.getContent());
        p.setAuthor(comment.getAuthor());
        postCommentRepository.save(p);
    }

    @Override
    public void deletePostComment(Long cno) throws Exception {
        postCommentRepository.deleteById(cno);
    }
}
