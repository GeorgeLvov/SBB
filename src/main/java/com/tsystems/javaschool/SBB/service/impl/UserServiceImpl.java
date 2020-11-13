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
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.addUser(user);
    }

    @Override
    @Transactional
    public UserDTO findUserDTOByName(String username) {
        User user = userRepository.findUserByName(username);
        return userMapper.toDTO(user);
    }

}
