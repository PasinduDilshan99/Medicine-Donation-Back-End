package com.nimbusnex.medicine_donation.repository;

import com.nimbusnex.medicine_donation.model.entitiy.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
            return rowsAffected > 0;
        } catch (Exception e) {
            LOGGER.error("Error creating user: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, username, password FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
}

