package com.expedia.demo.service;

import com.expedia.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();
    public User save(User user);
    public Optional<User> findOne(int id);
    public void deleteById(int id);
}
