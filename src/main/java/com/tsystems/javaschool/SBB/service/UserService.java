package com.tsystems.javaschool.SBB.service;

import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;

import java.util.List;

/**
 * Service class for {@link com.tsystems.javaschool.SBB.entities.User}
 *
 * @author George Lvov
 * @version 1.0
 */

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO findUserById(int id);

    UserDTO findByUsername(String username);

    void add(UserDTO userDTO);
}
