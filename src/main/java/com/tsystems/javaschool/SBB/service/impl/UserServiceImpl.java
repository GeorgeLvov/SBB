package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.mapper.UserMapper;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.repository.interfaces.UserRepository;
import com.tsystems.javaschool.SBB.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author George Lvov
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return userMapper.toDTOList(users);
    }

    @Override
    @Transactional
    public UserDTO findUserById(int id) {
        User user = userRepository.getUserById(id);
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.add(user);
    }

    @Override
    @Transactional
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDTO(user);
    }

}
