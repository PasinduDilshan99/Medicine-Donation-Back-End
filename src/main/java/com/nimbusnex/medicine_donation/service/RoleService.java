package com.nimbusnex.medicine_donation.service;

import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.InsertCommonResponse;
import com.nimbusnex.medicine_donation.model.response.UpdateCommonResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity<CommonResponse<List<Role>>> getAllRoles();

    ResponseEntity<CommonResponse<Role>> getRoleByName(String name);

    ResponseEntity<CommonResponse<Role>> getRoleById(Long id);

    ResponseEntity<CommonResponse<InsertCommonResponse>> addRole(Role role);

    ResponseEntity<CommonResponse<UpdateCommonResponse>> updateRoleDetails(Role role);
}
