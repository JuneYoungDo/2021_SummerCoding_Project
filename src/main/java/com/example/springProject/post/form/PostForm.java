package com.example.springProject.post.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
    private Long id;
    private String title;
    private String description;
}
