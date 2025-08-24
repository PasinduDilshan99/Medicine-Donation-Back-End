package com.nimbusnex.medicine_donation.security.repository.impl;

import com.nimbusnex.medicine_donation.model.entitiy.User;
import com.nimbusnex.medicine_donation.security.repository.UserRepositoryForSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryForSecurityImpl implements UserRepositoryForSecurity {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryForSecurityImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryForSecurityImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByUserName(String username) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
}
