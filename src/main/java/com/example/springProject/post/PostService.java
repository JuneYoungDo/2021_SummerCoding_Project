package com.example.springProject.post;

import com.example.springProject.comment.Comment;
import com.example.springProject.comment.CommentService;
import com.example.springProject.post.form.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public void save(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void update(Long postId, PostForm postForm) {
        Post post = findById(postId);
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post.getComments();
    }
}
