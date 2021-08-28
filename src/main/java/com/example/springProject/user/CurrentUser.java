package com.example.springProject.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'annoymousUser' ? null : user") // 등록되지 않은 유저면 null, 아니면 useraccount 반환
public @interface CurrentUser {
}
