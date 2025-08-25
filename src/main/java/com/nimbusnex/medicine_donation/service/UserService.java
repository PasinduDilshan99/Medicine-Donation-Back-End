package com.nimbusnex.medicine_donation.service;

import com.nimbusnex.medicine_donation.model.entitiy.User;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    <T> ResponseEntity<CommonResponse<T>> createUser(User user);

    <T> ResponseEntity<CommonResponse<T>> getAllUsers();
}
