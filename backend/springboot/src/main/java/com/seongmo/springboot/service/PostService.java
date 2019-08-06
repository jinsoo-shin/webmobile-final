package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Post;

import java.util.List;

public interface PostService {
    void insertPost(Post Post) throws Exception;
    List<Post> getAllPost() throws Exception;
    Post getPost(Long bno) throws Exception;
    void updatePost(Post Post) throws Exception;
    void deletePost(Long bno) throws Exception;
}
