package com.example.springProject.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private HashMap<Long, User> users = new HashMap<>();

    public User findById(Long id) {
        return users.get(id);
    }

    public Boolean existsById(Long id) {
        return users.get(id) != null;
    }

    public User findByName(String name) {
        return users.get(name);
    }

    public Boolean existsByName(String name) {
        return users.values()
                .stream()
                .anyMatch(user -> user.getName().equals(name));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void save(User user) {
        users.put(user.getId(),user);
    }
}
