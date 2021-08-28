package com.example.springProject.user;

import com.example.springProject.config.AppConfig;
import com.example.springProject.user.form.SignUpForm;
import com.example.springProject.user.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        //Optional<User>
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional      // 컴파일 단계에 저절로 transaction 해줌
    public void update(Long userId, UserForm userForm) {
        User user = findById(userId);
        user.setName(userForm.getName());
        user.setType(userForm.getType());
    }

    public User createUser(SignUpForm signUpForm) {
        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());

        User user = User.builder()
                .username((signUpForm.getUsername()))
                .password(encodedPassword)
                .name(signUpForm.getName())
                .type("USER")
                .build();

        return save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);  // 만약에 없다면 에러발생
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        return new UserAccount(user);
    }

    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(user),
                passwordEncoder.encode(user.getPassword()),
                List.of(new SimpleGrantedAuthority(user.getType()))
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
