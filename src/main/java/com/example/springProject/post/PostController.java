package com.example.springProject.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    /**
     * 게시글 목록 조회
     * GET /posts
     */
    @GetMapping("/posts")
    public String postIndex(Model model) {
        postService.save(new Post(1L,"제목1","내용1"));
        postService.save(new Post(2L,"제목2","내용2"));
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
     * 게시글 생성
     * POST /posts
     */

    /**
     * 게시글 수정
     * POST /posts/{post-id}
     */
}
