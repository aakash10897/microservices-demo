package com.expedia.demo.service.impl;

import com.expedia.demo.repository.UserRepository;
import com.expedia.demo.service.UserService;
import com.expedia.demo.model.User;

import java.util.List;
import java.util.Optional;

public class UserJpaService implements UserService {

    private UserRepository userRepository;

    public UserJpaService(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOne(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
