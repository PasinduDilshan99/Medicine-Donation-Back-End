package com.nimbusnex.medicine_donation.service.impl;

import com.nimbusnex.medicine_donation.exception.AlreadyExistsErrorExceptionHandler;
import com.nimbusnex.medicine_donation.exception.ValidationErrorExceptionHandler;
import com.nimbusnex.medicine_donation.model.entitiy.User;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;
import com.nimbusnex.medicine_donation.repository.UserRepository;
import com.nimbusnex.medicine_donation.service.UserService;
import com.nimbusnex.medicine_donation.service.ValidationService;
import com.nimbusnex.medicine_donation.util.ResponseCodesAndMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ValidationService validationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    @Override
    public ResponseEntity<CommonResponse<?>> createUser(User user) {
        try {
            validationService.validateUser(user);
            if (userRepository.createUser(user)) {
                return ResponseEntity.ok(new CommonResponse<>(
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_STATUS,
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_CODE,
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_MESSAGE,
                        true
                ));
            }
        } catch (ValidationErrorExceptionHandler validationErrorException) {
            throw new ValidationErrorExceptionHandler(validationErrorException.getMessage(), validationErrorException.getValidationFailedFieldResponsesList());
        } catch (AlreadyExistsErrorExceptionHandler alreadyExistsErrorException) {
            throw new AlreadyExistsErrorExceptionHandler(alreadyExistsErrorException.getMessage());
        } catch (Exception e) {

        }

        if (userCreateResponse.equals(ResponseCodesAndMessages.SUCCESSFULLY_CREATE_CODE)) {

        } else if (userCreateResponse.equals(ResponseCodesAndMessages.ALREADY_USER_EXIST_CODE)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CommonResponse<>(
                    ResponseCodesAndMessages.ALREADY_USER_EXIST_STATUS,
                    ResponseCodesAndMessages.ALREADY_USER_EXIST_CODE,
                    ResponseCodesAndMessages.ALREADY_USER_EXIST_MESSAGE,
                    false
            ));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(
                    ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_STATUS,
                    ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_CODE,
                    ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_MESSAGE,
                    false
            ));
        }
    }

}
