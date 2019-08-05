package com.seongmo.springboot.service.impl;

import com.seongmo.springboot.entity.Post;
import com.seongmo.springboot.repository.PostRepository;
import com.seongmo.springboot.service.PostService;
import com.seongmo.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public void insertPost(Post Post) throws Exception {
        postRepository.save(Post);
    }

    @Override
    public List<Post> getAllPost() throws Exception {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(String author) throws Exception {
        return postRepository.findByAuthor(author);
    }

    @Override
    public void updatePost(Post Post) throws Exception {
        Post p = postRepository.findByAuthor(Post.getAuthor());
        if(p != null) {
            p.setContent(Post.getContent());
            p.setTitle(Post.getTitle());
            postRepository.save(p);
        }
    }

    @Override
    public void deletePost(String author) throws Exception {
        postRepository.deleteByAuthor(author);
    }
}