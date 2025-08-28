package com.nimbusnex.medicine_donation.repository;

import com.nimbusnex.medicine_donation.model.entity.User;

import java.util.List;

public interface UserRepository {
    boolean createUser(User user);

    List<User> getAllUsers();
}
