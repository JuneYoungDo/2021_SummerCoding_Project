package com.example.springProject.user;

import com.example.springProject.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id     // key는 id
    @GeneratedValue(strategy = GenerationType.AUTO)     // 자동적으로 auto increament
    private Long id;

    private String username;
    private String password;

    private String name;
    private String type;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  // 하나의 유저가 여러 포스트를 갖는다.
                                    // post의 부모는 user이다
                                    // EAGER : 즉시로딩
                                    // LAZY : 지연로딩(default)
    private List<Post> posts;
}
