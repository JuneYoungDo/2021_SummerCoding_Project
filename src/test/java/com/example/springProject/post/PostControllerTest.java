package com.example.springProject.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("게시글 생성 폼 조회")
    void createPostForm() throws Exception {

        mockMvc.perform(get("/posts/new-post"))
                .andExpect(status().isOk())
                .andExpect(view().name("/posts/new-post"))
                .andExpect(model().attributeExists("postForm"));
    }

    // 게시글 생성 성공
    @Test
    @DisplayName("게시글 생성 - 성공")
    void creatUserSuccess() throws Exception {
        mockMvc.perform(post("/new-post")
                .param("id","1")
                .param("title","new_title")
                .param("description","description")
        ).andExpect(redirectedUrl("/posts"));

        Post post = postRepository.findById(1L);
        assertNotNull(post);
        assertThat(post.getTitle()).isEqualTo("new_title");
    }

    // 게시글 생성 실패
    @Test
    @DisplayName("게시글 생성 - 실패")
    void createPostFail() throws Exception {
        mockMvc.perform(post("/new-post")
                .param("title","new_title")
                .param("description","description")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("posts/new-post"))
                .andExpect(model().hasErrors());

        Post post = postRepository.findById(1L);
        assertNull(post);
    }

    // 게시글 조회
    @Test
    @DisplayName("게시글 조회 성공")
    void findPost() throws Exception {
        Post newPost = new Post(1L,"new_title","description");
        postRepository.save(newPost);

        mockMvc.perform(get("/posts/" + newPost.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/show"))
                .andExpect(model().attributeExists("post"));
    }

    // 게시글 수정 성공
    @Test
    @DisplayName("게시글 수정 - 성공")
    void editPostSuccess() throws Exception {
        Post newPost = new Post(1L,"title","description");
        postRepository.save(newPost);

        mockMvc.perform(post("/posts/edit-post/" + newPost.getId())
                .param("title","edit-title")
                .param("description","description")
        ).andExpect(redirectedUrl("/posts"));

        Post post = postRepository.findById(1L);
        assertNotNull(post);
        assertThat(post.getTitle()).isEqualTo("edit-title");
    }

}
