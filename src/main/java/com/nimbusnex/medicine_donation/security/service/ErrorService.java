package com.nimbusnex.medicine_donation.security.service;

import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthorizeRecordRequest;

public interface ErrorService {
    void insertUnAuthenticateRecord(InsertUnAuthenticateRecordRequest insertUnAuthenticateRecordRequest);

    void insertUnauthorizedAccessRecord(InsertUnAuthorizeRecordRequest insertUnAuthorizeRecordRequest);
}
