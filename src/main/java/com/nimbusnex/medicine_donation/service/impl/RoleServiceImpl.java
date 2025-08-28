package com.nimbusnex.medicine_donation.service.impl;

import com.nimbusnex.medicine_donation.exception.*;
import com.nimbusnex.medicine_donation.model.entity.Role;
import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.model.request.AddRoleRequest;
import com.nimbusnex.medicine_donation.model.request.UpdateRoleRequest;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.InsertCommonResponse;
import com.nimbusnex.medicine_donation.model.response.UpdateCommonResponse;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;
import com.nimbusnex.medicine_donation.repository.RoleRepository;
import com.nimbusnex.medicine_donation.security.service.CustomUserDetailsService;
import com.nimbusnex.medicine_donation.service.CommonService;
import com.nimbusnex.medicine_donation.service.RoleService;
import com.nimbusnex.medicine_donation.util.ResponseCodesAndMessages;
import com.nimbusnex.medicine_donation.validation.RoleValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;
    private final CommonService commonService;
    private final RoleValidation roleValidation;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           CommonService commonService,
                           RoleValidation roleValidation) {
        this.roleRepository = roleRepository;
        this.commonService = commonService;
        this.roleValidation = roleValidation;
    }


    @Override
    public ResponseEntity<CommonResponse<List<Role>>> getAllRoles() {
        try {
            List<Role> roleList = roleRepository.getAllRoles();
            return new ResponseEntity<>(
                    new CommonResponse<>(
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            roleList
                    ),
                    HttpStatus.OK
            );
        } catch (NoAnyDataFoundErrorExceptionHandler e) {
            throw new NoAnyDataFoundErrorExceptionHandler(e.getMessage());
        } catch (SearchDataErrorExceptionHandler e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CommonResponse<Role>> getRoleByName(String name) {
        try {
            ValidationResponse validationResponse = roleValidation.validateRoleName(name);
            if (!validationResponse.isValid()) {
                throw new ValidationErrorExceptionHandler(
                        "Role name validation failed.",
                        validationResponse.getValidationFailedFieldResponses());
            }
            Role role = roleRepository.getRoleByName(name);
            return new ResponseEntity<>(
                    new CommonResponse<>(
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            role
                    ),
                    HttpStatus.OK
            );
        }catch (NoAnyDataFoundErrorExceptionHandler e) {
            throw new NoAnyDataFoundErrorExceptionHandler(e.getMessage());
        } catch (SearchDataErrorExceptionHandler e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CommonResponse<Role>> getRoleById(Long id) {
        try {
            Role role = roleRepository.getRoleById(id);
            if (role == null) {
                throw new NoAnyDataFoundErrorExceptionHandler("Role not found");
            }
            return new ResponseEntity<>(
                    new CommonResponse<>(
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            role
                    ),
                    HttpStatus.OK
            );
        } catch (SearchDataErrorExceptionHandler e) {
            throw new SearchDataErrorExceptionHandler(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CommonResponse<InsertCommonResponse>> addRole(Role role) {
        Long loggedUserId = commonService.getLoggedUserId();
        if (loggedUserId == null) {
            throw new InternalServerErrorExceptionHandler("Logged user id is null");
        }
        Role searchedRole = roleRepository.getRoleByName(role.getName());
        if (searchedRole != null) {
            throw new AlreadyExistsErrorExceptionHandler("Already role is exist : " + role.getName());
        } else {
            try {
                roleRepository.addRole(new AddRoleRequest(
                        role.getName(),
                        role.getDescription(),
                        loggedUserId
                ));
                return new ResponseEntity<>(
                        new CommonResponse<>(
                                ResponseCodesAndMessages.SUCCESSFULLY_CREATE_STATUS,
                                ResponseCodesAndMessages.SUCCESSFULLY_CREATE_CODE,
                                ResponseCodesAndMessages.SUCCESSFULLY_CREATE_MESSAGE,
                                new InsertCommonResponse(
                                        true,
                                        "Successfully role created"
                                )
                        ),
                        HttpStatus.OK
                );
            } catch (InsertDataErrorExceptionHandler e) {
                throw new InsertDataErrorExceptionHandler(e.getMessage());
            } catch (Exception e) {
                throw new InternalServerErrorExceptionHandler(e.getMessage());
            }
        }

    }

    @Override
    public ResponseEntity<CommonResponse<UpdateCommonResponse>> updateRoleDetails(Role role) {
        Long loggedUserId = commonService.getLoggedUserId();
        if (loggedUserId == null) {
            throw new InternalServerErrorExceptionHandler("Logged user id is null");
        }
        Role searchedRole = roleRepository.getRoleByName(role.getName());
        if (searchedRole == null) {
            throw new NoAnyDataFoundErrorExceptionHandler("Role not found : " + role.getName());
        } else if (role.getName().equals(searchedRole.getName()) && role.getDescription().equals(searchedRole.getDescription())) {
            throw new PreviousDataAndNewDataSameErrorExceptionHandler("There is no update.");
        } else {
            try {
                roleRepository.updateRoleDetails(new UpdateRoleRequest(
                        searchedRole.getId(),
                        role.getName(),
                        role.getDescription(),
                        loggedUserId
                ));
                return new ResponseEntity<>(
                        new CommonResponse<>(
                                ResponseCodesAndMessages.SUCCESSFULLY_UPDATE_STATUS,
                                ResponseCodesAndMessages.SUCCESSFULLY_UPDATE_CODE,
                                ResponseCodesAndMessages.SUCCESSFULLY_UPDATE_MESSAGE,
                                new UpdateCommonResponse(
                                        true,
                                        "Successfully role updated"
                                )
                        ),
                        HttpStatus.OK
                );
            } catch (UpdateDataErrorExceptionHandler e) {
                throw new UpdateDataErrorExceptionHandler(e.getMessage());
            } catch (Exception e) {
                throw new InternalServerErrorExceptionHandler(e.getMessage());
            }
        }
    }
}
