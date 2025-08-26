package com.nimbusnex.medicine_donation.service;

import com.nimbusnex.medicine_donation.model.entitiy.User;
import com.nimbusnex.medicine_donation.model.response.ValidationResponse;

public interface ValidationService {
    void validateUser(User user);
}
