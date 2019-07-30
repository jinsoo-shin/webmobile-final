package com.seongmo.springboot.service;

import com.seongmo.springboot.entity.Post;

public interface PostService {
    public void insertPost(Post post) throws Exception;
    public Post getPost(String author) throws Exception;
    public void updatePost(Post post) throws Exception;
    public void deletePost(String author) throws Exception;
}
