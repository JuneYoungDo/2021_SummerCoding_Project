package com.example.springProject.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PostRepository {
    private HashMap<Long, Post> posts = new HashMap<>();

    public Post findById(Long postId) {
        return posts.get(postId);
    }
    public Post findByTitle(String postTitle) {
        return posts.get(postTitle);
    }
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }
    public void save(Post post) {
        posts.put(post.getId(),post);
    }

}
