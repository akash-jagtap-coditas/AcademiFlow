package com.example.AcademiFlow.service.Impl;

import com.example.AcademiFlow.entity.Users;

import com.example.AcademiFlow.config.UserDetailsImpl;
import com.example.AcademiFlow.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for loading user details by username.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepo;

    /**
     * Loads user details by username.
     *
     * @param username the username of the user
     * @return the UserDetails object
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Users user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        return UserDetailsImpl.build(user);
    }
}
