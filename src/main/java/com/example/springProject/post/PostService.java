package com.example.springProject.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }
    public Post findByTitle(String postTitle) {
        return postRepository.findByTitle(postTitle);
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public void save(Post post) {
        postRepository.save(post);
    }

}
