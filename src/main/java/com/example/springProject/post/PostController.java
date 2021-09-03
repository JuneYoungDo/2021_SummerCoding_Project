package com.example.springProject.post;

import com.example.springProject.post.form.PostForm;
import com.example.springProject.post.validator.PostFormValidator;
import com.example.springProject.user.CurrentUser;
import com.example.springProject.user.User;
import com.example.springProject.user.UserService;
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
public class PostController {

    private final PostService postService;
    private final PostFormValidator postFormValidator;
    private final UserService userService;
//    @InitBinder("postForm")
//    public void InitBinderPostForm(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(postFormValidator);
//    }

    /**
     * 게시글 목록 조회
     * GET /posts
     */
    @GetMapping("/posts")
    public String postIndex(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);

        return "posts/index";
    }

    /**
     * 게시글 상세 조회
     * GET /posts/{post_id}
     */
    @GetMapping("/posts/{postId}")
    public String showPost(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post",post);

        return "posts/show";
    }

    /**
     * 게시글 생성 폼 연결
     * GET /posts/new-post
     */
    @GetMapping("/posts/new-post")
    public String newPost(Model model) {
        model.addAttribute("postForm",new PostForm());
        return "/posts/new-post";
    }

    /**
     * 게시글 생성
     * POST /new-post
     */
    @PostMapping("/new-post")
    public String createPost(
            @CurrentUser User user,
            @Valid PostForm postForm, Errors errors) {
        if(errors.hasErrors()) {
            return "posts/new-post";
        }

        Post post = new Post(
                postForm.getId(),
                postForm.getTitle(),
                postForm.getDescription(),
                user,
                null
        );
        postService.save(post);

        return "redirect:/posts";
    }

    /**
     * 게시글 수정
     * POST /posts/edit-post/{post-id}
     */
    @GetMapping("/posts/edit-post/{postId}")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post",post);
        model.addAttribute("postForm",new PostForm(
                post.getId(),
                post.getTitle(),
                post.getDescription()
        ));

        return "posts/edit-post";
    }

    @PostMapping("posts/edit-post/{postId}")
    public String editPost(@PathVariable Long postId,PostForm postForm) {
        postService.update(postId,postForm);
        return "redirect:/posts";
    }
}
