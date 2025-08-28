package com.nimbusnex.medicine_donation.service.impl;

import com.nimbusnex.medicine_donation.exception.ValidationErrorExceptionHandler;
import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public void validateUser(User user) {
        if (user == null) {
            throw new ValidationErrorExceptionHandler("",null);
        }
    }
}
