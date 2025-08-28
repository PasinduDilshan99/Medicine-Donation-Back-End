package com.nimbusnex.medicine_donation.security.service.impl;

import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthorizeRecordRequest;
import com.nimbusnex.medicine_donation.security.repository.ErrorRepository;
import com.nimbusnex.medicine_donation.security.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ErrorServiceImpl implements ErrorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorServiceImpl.class);

    private final ErrorRepository errorRepository;

    public ErrorServiceImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Override
    public void insertUnAuthenticateRecord(InsertUnAuthenticateRecordRequest request) {
        Assert.notNull(request, "InsertUnAuthenticateRecordRequest must not be null");
        try {
            errorRepository.insertUnAuthenticateRecord(request);
            LOGGER.info("Unauthenticated record inserted for userId={}", request.getUserId());
        } catch (Exception e) {
            LOGGER.error("Failed to insert unauthenticated record for userId={}: {}",
                    request.getUserId(), e.getMessage(), e);
        }
    }

    @Override
    public void insertUnauthorizedAccessRecord(InsertUnAuthorizeRecordRequest request) {
        Assert.notNull(request, "InsertUnAuthorizeRecordRequest must not be null");
        try {
            errorRepository.insertUnauthorizedAccessRecord(request);
            LOGGER.info("Unauthorized access record inserted for userId={}", request.getUserId());
        } catch (Exception e) {
            LOGGER.error("Failed to insert unauthorized access record for userId={}: {}",
                    request.getUserId(), e.getMessage(), e);
        }
    }
}
