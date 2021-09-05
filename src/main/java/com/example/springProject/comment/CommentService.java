package com.example.springProject.comment;

import com.example.springProject.comment.form.CommentForm;
import com.example.springProject.post.Post;
import com.example.springProject.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void update(Long commentId, CommentForm commentForm) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setDescription(commentForm.getDescription());
    }

//    public Comment createComment(CommentForm commentForm) {
//        Comment comment = new Comment(
//                commentForm.getId(),
//                commentForm.getDescription(),
//
//        )
//    }


}
