package com.nimbusnex.medicine_donation.util;

public class ResponseCodesAndMessages {
    // SUCCESS
    public static final int SUCCESS_STATUS = 200;
    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String SUCCESS_MESSAGES = "Success";

    // SUCCESSFULLY_CREATE
    public static final int SUCCESSFULLY_CREATE_STATUS = 200;
    public static final String SUCCESSFULLY_CREATE_CODE = "SUCCESSFULLY_CREATE";
    public static final String SUCCESSFULLY_CREATE_MESSAGE = "SUCCESSFULLY_CREATE";

    // ALREADY_USER_EXIST
    public static final int ALREADY_USER_EXIST_STATUS = 200;
    public static final String ALREADY_USER_EXIST_CODE = "ALREADY_USER_EXIST";
    public static final String ALREADY_USER_EXIST_MESSAGE = "ALREADY_USER_EXIST";

    // UNSUCCESS
    public static final int UNSUCCESS_STATUS = 400;
    public static final String UNSUCCESS_CODE = "UNSUCCESS";
    public static final String UNSUCCESS_MESSAGES = "Unsuccess";

    // BAD_REQUEST
    public static final int BAD_REQUEST_STATUS = 404;
    public static final String BAD_REQUEST_CODE = "BAD_REQUEST";
    public static final String BAD_REQUEST_MESSAGES = "Bad request";

    // INVALID_ARGUMENT
    public static final int INVALID_ARGUMENT_STATUS = 400;
    public static final String INVALID_ARGUMENT_CODE = "INVALID_ARGUMENT";
    public static final String INVALID_ARGUMENT_MESSAGES = "Client specified an invalid argument, request body or query param.";

    //    NOT_FOUND
    public static final int NOT_FOUND_STATUS = 404;
    public static final String NOT_FOUND_CODE = "NOT_FOUND";
    public static final String NOT_FOUND_MESSAGE = "is not found";

    //    INTERNAL_SERVER_ERROR
    public static final int INTERNAL_SERVER_ERROR_STATUS = 500;
    public static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    //    INTERNAL
    public static final int INVALID_DATA_STATUS = 400;
    public static final String INVALID_DATA_CODE = "INVALID_DATA";
    public static final String INVALID_DATA_MESSAGE = "Load invalid data from database";

    // Validation Error
    public static final int VALIDATION_ERROR_STATUS = 422;
    public static final String VALIDATION_ERROR_CODE = "VALIDATION_ERROR";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation failed for the input data";

    // Convert Error
    public static final int CONVERT_ERROR_STATUS = 400;
    public static final String CONVERT_ERROR_CODE = "CONVERT_ERROR";
    public static final String CONVERT_ERROR_MESSAGE = "Error Occur when converting.";

    // Unauthorized Error
    public static final int UNAUTHORIZED_ERROR_STATUS = 403;
    public static final String UNAUTHORIZED_ERROR_CODE = "UNAUTHORIZED";
    public static final String UNAUTHORIZED_ERROR_MESSAGE = "You do not have permission to access this resource.";

    // Unauthenticated Error
    public static final int UNAUTHENTICATED_ERROR_STATUS = 401;
    public static final String UNAUTHENTICATED_ERROR_CODE = "UNAUTHENTICATED";
    public static final String UNAUTHENTICATED_ERROR_MESSAGE = "Authentication is required to access this resource.";
}