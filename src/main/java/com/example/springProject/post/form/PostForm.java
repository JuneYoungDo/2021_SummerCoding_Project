package com.example.springProject.post.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
