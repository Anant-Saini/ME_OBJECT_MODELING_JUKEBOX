package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements CRUDRepository<User, String> {

    private final Map<String, User> userMap;
    private Integer autoincrement;

    public UserRepository() {
        this.userMap = new HashMap<>();
        this.autoincrement = 0;
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoincrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if(entity.getId() == null) {
            this.autoincrement++;
            User user = new User(Integer.toString(this.autoincrement), entity.getName());
            this.userMap.put(user.getId(), user);
            return user;
        } else {
            this.userMap.put(entity.getId(), entity);
            return entity;
        }

    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(this.userMap.get(id));
    }

    @Override
    public List<User> findAll() {
        
        return this.userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByName(String name) {
        return this.userMap.values().stream().filter(sng -> sng.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<User> delete(User entity) {
        
        return Optional.ofNullable(this.userMap.remove(entity.getId()));
    }
    
}