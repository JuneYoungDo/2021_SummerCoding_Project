package com.example.springProject.user;

import com.example.springProject.user.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 유저 목록 조회
     * GET /users
     */
    @GetMapping("/users")
    public String index(Model model) {
        userService.save(new User(1L,"하이","하이"));
        userService.save(new User(2L,"하이1","하이1"));
        List<User> users = userService.findAll();
        model.addAttribute("users",users);

        return "users/index";
    }

    /**
     * 유저 상세 조회
     * GET /users/{user_id}
     */
    @GetMapping("/users/{userId}")
    public String show(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user",user);

        return "users/show";
    }

    /**
     *
     */
    @GetMapping("/users/new-user")
    public String newUser(Model model) {
        model.addAttribute("userForm",new UserForm());

        return "users/new-user";
    }

    /**
     * 유저 생성
     * POST /users
     */
    @PostMapping("/new-user")
    public String create(UserForm userForm) {
        User user = new User(
                userForm.getId(),
                userForm.getName(),
                userForm.getType()
        );
        userService.save(user);

        return "redirect:/users";
    }


    /**
     * 유저 수정
     * POST /users/{user_id}
     */
    @GetMapping("/users/edit-user/{userId}")
    public String editUser(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user",user);
        model.addAttribute("userForm",new UserForm(
                user.getId(),
                user.getName(),
                user.getType()
        ));

        return "users/edit-user";
    }
    /**
     *
     */
    @PostMapping("/users/edit-user/{userId}")
    public String editUser(@PathVariable Long userId, UserForm userForm) {
        User user = userService.findById(userId);
        user.setId(userForm.getId());
        user.setName(userForm.getName());
        user.setType(userForm.getType());

        return "redirect:/users";
    }
}
