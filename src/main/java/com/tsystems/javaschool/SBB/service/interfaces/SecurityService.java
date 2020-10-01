package com.tsystems.javaschool.SBB.service.interfaces;

/**
 * Service for Security.
 *
 * @author George Lvov
 * @version 1.0
 */

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}