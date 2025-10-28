package com.crio.jukebox.services;

import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.entities.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name) {
        return userRepository.save(new User(name));
    }
    
}
