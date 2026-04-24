package com.bookstore.user_service.service;

import com.bookstore.user_service.dto.UserRequest;
import com.bookstore.user_service.entity.User;
import com.bookstore.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, UserRequest request) {
        User user = getUserById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }
}