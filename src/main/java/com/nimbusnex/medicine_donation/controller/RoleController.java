package com.nimbusnex.medicine_donation.controller;

import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.request.GetRoleByNameRequest;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.InsertCommonResponse;
import com.nimbusnex.medicine_donation.model.response.UpdateCommonResponse;
import com.nimbusnex.medicine_donation.service.RoleService;
import com.nimbusnex.medicine_donation.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v0/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/roles")
    public ResponseEntity<CommonResponse<List<Role>>> getAllRoles() {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<List<Role>>> response = roleService.getAllRoles();
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/role")
    public ResponseEntity<CommonResponse<Role>> getRoleByName(@RequestBody GetRoleByNameRequest getRoleByNameRequest) {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<Role>> response = roleService.getRoleByName(getRoleByNameRequest.getRoleName());
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<Role>> getRoleById(@PathVariable Long id) {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<Role>> response = roleService.getRoleById(id);
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/role")
    public ResponseEntity<CommonResponse<InsertCommonResponse>> addRole(@RequestBody Role role) {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<InsertCommonResponse>> response = roleService.addRole(role);
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PutMapping(path = "/role")
    public ResponseEntity<CommonResponse<UpdateCommonResponse>> updateRoleDetails(@RequestBody Role role) {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<UpdateCommonResponse>> response = roleService.updateRoleDetails(role);
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

}
