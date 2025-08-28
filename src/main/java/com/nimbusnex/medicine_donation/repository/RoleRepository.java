package com.nimbusnex.medicine_donation.repository;

import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.request.AddRoleRequest;
import com.nimbusnex.medicine_donation.model.request.UpdateRoleRequest;

import java.util.List;

public interface RoleRepository {
    List<Role> getAllRoles();

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    void addRole(AddRoleRequest addRoleRequest);

    void updateRoleDetails(UpdateRoleRequest updateRoleRequest);
}
