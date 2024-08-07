package com.aicarebot.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aicarebot.api.daos.User;
import com.aicarebot.api.mappers.UserMapper;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
      }
    
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }

    public User addUser(User user) {
        userMapper.insert(user);
        return user;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        updatedUser.setId(id);
        userMapper.update(updatedUser);
        return Optional.of(updatedUser);
    }

    public void deleteUser(Long id) {
        userMapper.delete(id);
    }
    
}
