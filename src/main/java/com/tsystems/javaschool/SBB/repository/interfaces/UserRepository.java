package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.User;

import java.util.List;

public interface UserRepository {
    
    List<User> getAllUsers();

    User getUserById(int id);

    User findByUsername(String username);

    void add(User user);

}
