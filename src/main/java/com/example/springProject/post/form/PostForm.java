package com.example.springProject.post.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
