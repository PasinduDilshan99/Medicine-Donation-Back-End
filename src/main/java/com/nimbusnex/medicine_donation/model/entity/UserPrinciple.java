package com.nimbusnex.medicine_donation.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserPrinciple implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPrinciple.class);

    private User user;

    public UserPrinciple(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // If no roles are found, return default USER role
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // Convert roles to authorities
        List<SimpleGrantedAuthority> roleAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        // Convert privileges to authorities (optional - you can choose to include or exclude this)
        List<SimpleGrantedAuthority> privilegeAuthorities = Collections.emptyList();
        if (user.getPrivileges() != null && !user.getPrivileges().isEmpty()) {
            privilegeAuthorities = user.getPrivileges().stream()
                    .map(privilege -> new SimpleGrantedAuthority("PRIVILEGE_" + privilege))
                    .collect(Collectors.toList());
        }

        LOGGER.info("User authorities: {}", roleAuthorities);
        LOGGER.info("User privileges: {}", privilegeAuthorities);

        // Combine roles and privileges
        List<SimpleGrantedAuthority> collect = Stream.concat(roleAuthorities.stream(), privilegeAuthorities.stream())
                .collect(Collectors.toList());
        LOGGER.info("User combined authorities: {}", collect);
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    // Getter for the user object
    public User getUser() {
        return user;
    }
}