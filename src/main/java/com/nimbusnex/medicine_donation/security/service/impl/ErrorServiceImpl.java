package com.nimbusnex.medicine_donation.security.service.impl;

import com.nimbusnex.medicine_donation.model.request.InsertJwtTokenErrorRecordRequest;
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
    public void insertUnAuthenticateRecord(InsertUnAuthenticateRecordRequest insertUnAuthenticateRecordRequest) {
        Assert.notNull(insertUnAuthenticateRecordRequest, "InsertUnAuthenticateRecordRequest must not be null");
        try {
            errorRepository.insertUnAuthenticateRecord(insertUnAuthenticateRecordRequest);
            LOGGER.info("Unauthenticated record inserted for userId={}", insertUnAuthenticateRecordRequest.getUserId());
        } catch (Exception e) {
            LOGGER.error("Failed to insert unauthenticated record for userId={}: {}",
                    insertUnAuthenticateRecordRequest.getUserId(), e.getMessage(), e);
        }
    }

    @Override
    public void insertUnauthorizedAccessRecord(InsertUnAuthorizeRecordRequest insertUnAuthorizeRecordRequest) {
        Assert.notNull(insertUnAuthorizeRecordRequest, "InsertUnAuthorizeRecordRequest must not be null");
        try {
            errorRepository.insertUnauthorizedAccessRecord(insertUnAuthorizeRecordRequest);
            LOGGER.info("Unauthorized access record inserted for userId={}", insertUnAuthorizeRecordRequest.getUserId());
        } catch (Exception e) {
            LOGGER.error("Failed to insert unauthorized access record for userId={}: {}",
                    insertUnAuthorizeRecordRequest.getUserId(), e.getMessage(), e);
        }
    }

    @Override
    public void insertJwtTokenErrorRecord(InsertJwtTokenErrorRecordRequest insertJwtTokenErrorRecordRequest) {
        Assert.notNull(insertJwtTokenErrorRecordRequest, "InsertJwtTokenErrorRecordRequest must not be null");
        try {
            errorRepository.insertJwtTokenErrorRecord(insertJwtTokenErrorRecordRequest);
            LOGGER.info("Unauthorized access record inserted for userId={}", insertJwtTokenErrorRecordRequest.getUserId());
        } catch (Exception e) {
            LOGGER.error("Failed to insert unauthorized access record for userId={}: {}",
                    insertJwtTokenErrorRecordRequest.getUserId(), e.getMessage(), e);
        }
    }
}
