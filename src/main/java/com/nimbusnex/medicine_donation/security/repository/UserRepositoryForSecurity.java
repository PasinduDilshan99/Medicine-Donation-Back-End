package com.nimbusnex.medicine_donation.security.repository;

import com.nimbusnex.medicine_donation.model.entitiy.User;

public interface UserRepositoryForSecurity {
    User getUserByUserName(String username);
}
