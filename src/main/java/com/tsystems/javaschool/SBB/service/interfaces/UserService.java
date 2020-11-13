package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.UserDTO;


import java.util.List;

/**
 * Interface for service class for {@link com.tsystems.javaschool.SBB.entities.User}
 *
 * @author George Lvov
 * @version 1.0
 */

public interface UserService {

    UserDTO findUserDTOByName(String username);

    void register(UserDTO userDTO);
}
