package com.example.springProject.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 생성 폼 조회")
    void createUserFrom() throws Exception {
        // when , then
        mockMvc.perform(get("/users/new-user")) // get방식과 경로 전달
                .andExpect(status().isOk())     // 상태코드 200
                .andExpect(view().name("users/new-user"))   //리턴하는 뷰 이름
                .andExpect(model().attributeExists("userForm"));    // 모델 정보 검증
    }

    // 유저 생성 성공
    @Test
    @DisplayName("유저 생성 - 성공")
    void createUserSuccess() throws Exception {
        mockMvc.perform(post("/new-user")
                .param("id","1")
                .param("name","new_name")
                .param("type","type")
        ).andExpect(redirectedUrl("/users"));   // redirect 검증

        User user = userRepository.findById(1L).get();
        assertNotNull(user);
        assertThat(user.getName()).isEqualTo("new_name");
    }

    // 유저 생성 실패
    @Test
    @DisplayName("유저 생성 - 실패")
    void createUserFail() throws Exception {
        mockMvc.perform(post("/new-user")
                .param("type","type")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("users/new-user"))
                .andExpect(model().hasErrors());

        User user = userRepository.findById(1L).get();
        assertNull(user);
    }

    // 유저 조회
    @Test
    @DisplayName("유저 조회 성공")
    void findUser() throws Exception {
        User newUser = new User(1L,"new_name","type",new ArrayList<>());
        userRepository.save(newUser);

        mockMvc.perform(get("/users/" + newUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/show"))
                .andExpect(model().attributeExists("user"));
    }

    // 유저 수정 성공
    @Test
    @DisplayName("유저 수정 - 성공")
    void editUserSuccess() throws Exception {
        User newUser  = new User(1L,"name","type",new ArrayList<>());
        userRepository.save(newUser);

        mockMvc.perform(post("/users/edit-user/" + newUser.getId())
                .param("name","edit_name")
                .param("type","type")
        ).andExpect(redirectedUrl("/users"));

        User user = userRepository.findById(1L).get();
        assertNotNull(user);
        assertThat(user.getName()).isEqualTo("edit_name");
    }
}
