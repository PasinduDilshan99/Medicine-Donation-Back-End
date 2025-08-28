package com.nimbusnex.medicine_donation.repository.impl;

import com.nimbusnex.medicine_donation.exception.InsertDataErrorExceptionHandler;
import com.nimbusnex.medicine_donation.exception.NoAnyDataFoundErrorExceptionHandler;
import com.nimbusnex.medicine_donation.exception.SearchDataErrorExceptionHandler;
import com.nimbusnex.medicine_donation.exception.UpdateDataErrorExceptionHandler;
import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.request.AddRoleRequest;
import com.nimbusnex.medicine_donation.model.request.UpdateRoleRequest;
import com.nimbusnex.medicine_donation.queries.RoleQueries;
import com.nimbusnex.medicine_donation.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getAllRoles() {
        String GET_ALL_ROLES = RoleQueries.GET_ALL_ROLLS;

        try {
            return jdbcTemplate.query(GET_ALL_ROLES, (rs, rowNum) -> {
                Role role = new Role();
                role.setId(rs.getLong("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));
                role.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                role.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                role.setCreatedBy(rs.getLong("created_by"));
                role.setUpdatedBy(rs.getLong("updated_by"));
                return role;
            });
        } catch (EmptyResultDataAccessException e) {
            throw new NoAnyDataFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public Role getRoleByName(String name) {
        String GET_ROLE_BY_NAME = RoleQueries.GET_ROLE_BY_NAME;
        try {
            return jdbcTemplate.queryForObject(GET_ROLE_BY_NAME, (rs, rowNum) -> {
                Role role = new Role();
                role.setId(rs.getLong("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));
                role.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                role.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                role.setCreatedBy(rs.getLong("created_by"));
                role.setUpdatedBy(rs.getLong("updated_by"));
                return role;
            }, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public Role getRoleById(Long id) {
        String GET_ROLE_BY_ID = RoleQueries.GET_ROLE_BY_ID;
        try {
            return jdbcTemplate.queryForObject(GET_ROLE_BY_ID, (rs, rowNum) -> {
                Role role = new Role();
                role.setId(rs.getLong("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));
                role.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                role.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                role.setCreatedBy(rs.getLong("created_by"));
                role.setUpdatedBy(rs.getLong("updated_by"));
                return role;
            }, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoAnyDataFoundErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public void addRole(AddRoleRequest addRoleRequest) {
        String ADD_ROLE = RoleQueries.ADD_ROLE;
        try {
            jdbcTemplate.update(ADD_ROLE,
                    addRoleRequest.getRoleName(),
                    addRoleRequest.getRoleDescription(),
                    addRoleRequest.getUserId(),
                    null);
        } catch (Exception e) {
            throw new InsertDataErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public void updateRoleDetails(UpdateRoleRequest updateRoleRequest) {
        String UPDATE_ROLE_DETAILS = RoleQueries.UPDATE_ROLE_DETAILS;
        try {
            jdbcTemplate.update(UPDATE_ROLE_DETAILS,
                    updateRoleRequest.getRoleName(),
                    updateRoleRequest.getRoleDescription(),
                    updateRoleRequest.getUserId(),
                    updateRoleRequest.getRoleId()
                    );
        } catch (Exception e) {
            throw new UpdateDataErrorExceptionHandler(e.getMessage());
        }
    }

}
