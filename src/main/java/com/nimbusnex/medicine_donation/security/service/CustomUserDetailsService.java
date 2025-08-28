package com.nimbusnex.medicine_donation.security.service;

import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.model.entity.UserPrinciple;
import com.nimbusnex.medicine_donation.security.repository.UserRepositoryForSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepositoryForSecurity userRepositoryForSecurity;

    @Autowired
    public CustomUserDetailsService(UserRepositoryForSecurity userRepositoryForSecurity) {
        this.userRepositoryForSecurity = userRepositoryForSecurity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryForSecurity.getUserByUserName(username);
        LOGGER.info("User found: {}", user);
        if (user == null){
            LOGGER.info("User not found");
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserPrinciple(user);
    }

}
