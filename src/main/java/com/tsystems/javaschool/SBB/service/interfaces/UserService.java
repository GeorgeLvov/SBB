package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.UserDTO;


import java.util.List;

/**
 * Service class for {@link com.tsystems.javaschool.SBB.entities.User}
 *
 * @author George Lvov
 * @version 1.0
 */

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO findUserDTOById(int id);

    UserDTO findUserDTOByName(String username);

    void registry(UserDTO userDTO);
}
