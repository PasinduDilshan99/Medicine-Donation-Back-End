package com.nimbusnex.medicine_donation.service.impl;

import com.nimbusnex.medicine_donation.model.entity.User;
import com.nimbusnex.medicine_donation.model.entity.UserPrinciple;
import com.nimbusnex.medicine_donation.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);


    @Override
    public Long getLoggedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserPrinciple) {
                UserPrinciple userPrinciple = (UserPrinciple) principal;
                Long userId = userPrinciple.getUser().getId();
                return userId;
            } else {
                LOGGER.info("Principal is not UserPrinciple, actual type: " + principal.getClass());
            }
        } else {
            LOGGER.info("No authenticated user found");
        }
        return null;
    }

}
