package com.nimbusnex.medicine_donation.security.repository.impl;

import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.security.repository.UserRepositoryForSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            User u = new User();
            u.setId(rs.getLong("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            return u;
        });

        // Fetch roles for the user
        if (user != null) {
            List<String> roles = getUserRoles(user.getId());
            user.setRoles(roles);

            // Optionally fetch privileges as well
            List<String> privileges = getUserPrivileges(user.getId());
            user.setPrivileges(privileges);
        }

        return user;
    }

    private List<String> getUserRoles(Long userId) {
        String sql = """
            SELECT r.name
            FROM roles r
            JOIN user_roles ur ON r.id = ur.role_id
            WHERE ur.user_id = ?
            """;

        return jdbcTemplate.queryForList(sql, String.class, userId);
    }

    private List<String> getUserPrivileges(Long userId) {
        String sql = """
            SELECT DISTINCT p.name
            FROM privileges p
            JOIN role_privileges rp ON p.id = rp.privilege_id
            JOIN roles r ON rp.role_id = r.id
            JOIN user_roles ur ON r.id = ur.role_id
            WHERE ur.user_id = ?
            """;

        return jdbcTemplate.queryForList(sql, String.class, userId);
    }
}