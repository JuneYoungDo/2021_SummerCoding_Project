package com.example.springProject.user;

import com.example.springProject.user.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        //Optional<User>
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional      // 컴파일 단계에 저절로 transaction 해줌
    public void update(Long userId, UserForm userForm) {
        User user = findById(userId);
        user.setName(userForm.getName());
        user.setType(userForm.getType());
    }
}
