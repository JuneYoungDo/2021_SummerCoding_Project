package com.example.springProject.comment.validator;

import com.example.springProject.comment.Comment;
import com.example.springProject.comment.CommentRepository;
import com.example.springProject.comment.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CommentFormValidator implements Validator {

    private final CommentRepository commentRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Comment comment = (Comment) target;

    }
}
