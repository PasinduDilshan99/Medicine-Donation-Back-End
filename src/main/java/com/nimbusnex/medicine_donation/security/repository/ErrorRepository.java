package com.nimbusnex.medicine_donation.security.repository;

import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthorizeRecordRequest;

public interface ErrorRepository {
    void insertUnAuthenticateRecord(InsertUnAuthenticateRecordRequest insertUnAuthenticateRecordRequest);

    void insertUnauthorizedAccessRecord(InsertUnAuthorizeRecordRequest insertUnAuthorizeRecordRequest);
}
