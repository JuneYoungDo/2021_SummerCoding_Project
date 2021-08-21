package com.example.springProject.post.validator;


import com.example.springProject.post.PostRepository;
import com.example.springProject.post.form.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PostFormValidator implements Validator {

    private final PostRepository postRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostForm postForm = (PostForm) target;

        if(postRepository.existsById(postForm.getId())) {
            errors.rejectValue("id","exists-value","이미 존재하는 아이디 입니다.");
        }
    }
}
