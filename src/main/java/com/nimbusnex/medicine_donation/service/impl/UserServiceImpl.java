package com.nimbusnex.medicine_donation.service.impl;

import com.nimbusnex.medicine_donation.exception.AlreadyExistsErrorExceptionHandler;
import com.nimbusnex.medicine_donation.exception.InternalServerErrorExceptionHandler;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    @Override
    public ResponseEntity<CommonResponse<Boolean>> createUser(User user) {
        try {
            validationService.validateUser(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userRepository.createUser(user)) {
                return ResponseEntity.ok(new CommonResponse<>(
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_STATUS,
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_CODE,
                        ResponseCodesAndMessages.SUCCESSFULLY_CREATE_MESSAGE,
                        true
                ));
            }else{
                throw new InternalServerErrorExceptionHandler("something went wrong.");
            }
        } catch (ValidationErrorExceptionHandler validationErrorException) {
            throw new ValidationErrorExceptionHandler(validationErrorException.getMessage(), validationErrorException.getValidationFailedFieldResponsesList());
        } catch (AlreadyExistsErrorExceptionHandler alreadyExistsErrorException) {
            throw new AlreadyExistsErrorExceptionHandler(alreadyExistsErrorException.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CommonResponse<List<User>>> getAllUsers() {
        try {
            List<User> users = userRepository.getAllUsers();
            return new ResponseEntity<>(
                    new CommonResponse<>(
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_STATUS,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_CODE,
                            ResponseCodesAndMessages.SUCCESSFULLY_RETRIEVE_MESSAGE,
                            users
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            throw new InternalServerErrorExceptionHandler(e.getMessage());
        }
    }

}
