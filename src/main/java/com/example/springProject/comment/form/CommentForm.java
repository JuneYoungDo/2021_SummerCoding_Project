package com.example.springProject.comment.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentForm {
    private Long id;
    @NotNull
    private Long postId;
    @NotBlank
    private String description;
}
