package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.PostComment;

import java.util.List;

public interface PostCommentService {
    void insertPostComment(PostComment postComment) throws Exception;
    List<PostComment> getAllPostComment(Long bno) throws Exception;
    PostComment getPostComment(Long cno) throws Exception;
    void updatePostComment(PostComment postComment) throws Exception;
    void deletePostComment(Long cno) throws Exception;
}
