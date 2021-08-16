package com.example.springProject.user.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
}
