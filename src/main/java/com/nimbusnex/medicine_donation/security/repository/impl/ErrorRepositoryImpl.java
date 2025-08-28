package com.nimbusnex.medicine_donation.security.repository.impl;

import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthorizeRecordRequest;
import com.nimbusnex.medicine_donation.queries.ErrorQueries;
import com.nimbusnex.medicine_donation.security.repository.ErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorRepositoryImpl implements ErrorRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ErrorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertUnAuthenticateRecord(InsertUnAuthenticateRecordRequest request) {
        String sql = ErrorQueries.INSERT_UNAUTHENTICATE_RECORD;

        try {
            jdbcTemplate.update(
                    sql,
                    request.getStatus(),
                    request.getCode(),
                    request.getMessage(),
                    request.getTimestamp(),
                    request.getErrorMessage(),
                    request.getPath(),
                    request.getUserId(),
                 request.getEnteredPassword()
            );
            LOGGER.info("Unauthenticated record inserted for userId={}", request.getUserId());
        } catch (DataAccessException e) {
            LOGGER.error("Failed to insert unauthenticated record for userId={} : {}",
                    request.getUserId(), e.getMessage(), e);
        }
    }

    @Override
    public void insertUnauthorizedAccessRecord(InsertUnAuthorizeRecordRequest insertUnAuthorizeRecordRequest) {
        String sql =ErrorQueries.INSERT_UNAUTHORIZE_RECORD;

        try {
            jdbcTemplate.update(
                    sql,
                    insertUnAuthorizeRecordRequest.getStatus(),
                    insertUnAuthorizeRecordRequest.getCode(),
                    insertUnAuthorizeRecordRequest.getMessage(),
                    insertUnAuthorizeRecordRequest.getTimestamp(),
                    insertUnAuthorizeRecordRequest.getErrorMessage(),
                    insertUnAuthorizeRecordRequest.getPath(),
                    insertUnAuthorizeRecordRequest.getUserId()
            );
            LOGGER.info("Unauthorized access record inserted for userId={}", insertUnAuthorizeRecordRequest.getUserId());
        } catch (DataAccessException e) {
            LOGGER.error("Failed to insert unauthorized access record for userId={} : {}",
                    insertUnAuthorizeRecordRequest.getUserId(), e.getMessage(), e);
        }
    }

}
