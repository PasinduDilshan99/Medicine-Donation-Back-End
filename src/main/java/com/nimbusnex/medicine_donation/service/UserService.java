package com.nimbusnex.medicine_donation.service;

import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.model.request.LoginRequest;
import com.nimbusnex.medicine_donation.model.response.AuthenticateResponse;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    <T> ResponseEntity<CommonResponse<T>> createUser(User user);

    <T> ResponseEntity<CommonResponse<T>> getAllUsers();

    ResponseEntity<CommonResponse<AuthenticateResponse>> userAuthenticate(LoginRequest loginRequest);
}
