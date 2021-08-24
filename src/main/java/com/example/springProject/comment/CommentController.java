package com.example.springProject.comment;

import com.example.springProject.comment.form.CommentForm;
import com.example.springProject.comment.validator.CommentFormValidator;
import com.example.springProject.post.Post;
import com.example.springProject.post.PostService;
import com.example.springProject.user.User;
import com.example.springProject.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentFormValidator commentFormValidator;
    private final UserService userService;
    private final PostService postService;

//    @InitBinder
//    public void InitBinderCommentForm(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(commentFormValidator);
//    }

    /**
     * 댓글 목록 조회
     * GET /comments
     */
    @GetMapping("/comments")
    public String commentIdx(Model model) {
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);

        return "comments/index";
    }

    /**
     * 댓글 생성 폼 연결
     * GET /comments/new-comment
     */
    @GetMapping("/comments/new-comment")
    public String newComment(Model model) {
        model.addAttribute("commentForm", new CommentForm());
        return "comments/new-comment";
    }

    /**
     * 댓글 생성
     * POST /comments/new-comment
     */
    @PostMapping("/new-comment")
    public String createComment(@Valid CommentForm commentForm, Errors errors) {
        if (errors.hasErrors()) {
            return "comments/new-comment";
        }
        User user = userService.findById(1L);

        Comment comment = new Comment(
                commentForm.getId(),
                commentForm.getDescription(),
                user,
                postService.findById(commentForm.getPostId())
        );
        commentService.save(comment);

        return "redirect:/comments";
    }

    /**
     * 댓글 수정
     * POST /comments/edit-comment/{commentId}
     */
    @GetMapping("/comments/edit-comment/{commentId}")
    public String editComment(@PathVariable Long commentId,Model model) {
        Comment comment = commentService.findById(commentId);
        model.addAttribute("comment",comment);
        model.addAttribute("commentForm",new CommentForm(
                comment.getId(),
                comment.getPost().getId(),
                comment.getDescription()
        ));

        return "comments/edit-comment";
    }
    @PostMapping("/comments/edit-comment/{commentId}")
    public String editComment(@PathVariable Long commentId, CommentForm commentForm) {
        commentService.update(commentId,commentForm);
        return "redirect:/comments";
    }
}
