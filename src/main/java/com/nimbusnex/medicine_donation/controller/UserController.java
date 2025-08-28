package com.nimbusnex.medicine_donation.controller;

import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.model.request.LoginRequest;
import com.nimbusnex.medicine_donation.model.response.AuthenticateResponse;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.service.UserService;
import com.nimbusnex.medicine_donation.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v0/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public <T> ResponseEntity<CommonResponse<T>> getAllUsers() {
        LOGGER.info("{} Start execute get all users {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<T>> response = userService.getAllUsers();
        LOGGER.info("{} End execute get all users {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/user")
    public <T> ResponseEntity<CommonResponse<T>> createUser(@RequestBody User user) {
        LOGGER.info("{} Start execute create user {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<T>> response = userService.createUser(user);
        LOGGER.info("{} End execute create user {}", Constant.DOTS, Constant.DOTS);
        return response;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<AuthenticateResponse>> login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("{} Start execute login {}", Constant.DOTS, Constant.DOTS);
        ResponseEntity<CommonResponse<AuthenticateResponse>> commonResponseResponseEntity = userService.userAuthenticate(loginRequest);
        LOGGER.info("{} End execute login {}", Constant.DOTS, Constant.DOTS);
        return commonResponseResponseEntity;
    }


}
