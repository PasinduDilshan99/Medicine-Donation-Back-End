package com.nimbusnex.medicine_donation.exception;

import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.ValidationFailedFieldResponses;
import com.nimbusnex.medicine_donation.util.Constant;
import com.nimbusnex.medicine_donation.util.ResponseCodesAndMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<List<ValidationFailedFieldResponses>>> handleValidationErrorException(ValidationErrorExceptionHandler e) {
        logger.error("{} Validation Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.VALIDATION_ERROR_STATUS,
                ResponseCodesAndMessages.VALIDATION_ERROR_CODE,
                ResponseCodesAndMessages.VALIDATION_ERROR_MESSAGE,
                e.getValidationFailedFieldResponsesList()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {AlreadyExistsErrorExceptionHandler.class})
    public <T> ResponseEntity<CommonResponse<List<T>>> handleAlreadyExistsErrorException(AlreadyExistsErrorExceptionHandler e) {
        logger.error("{} Already Exists Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.ALREADY_USER_EXIST_STATUS,
                ResponseCodesAndMessages.ALREADY_USER_EXIST_CODE,
                ResponseCodesAndMessages.ALREADY_USER_EXIST_MESSAGE,
                null
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {InternalServerErrorExceptionHandler.class})
    public <T> ResponseEntity<CommonResponse<List<T>>> handleInternalServerErrorException(AlreadyExistsErrorExceptionHandler e) {
        logger.error("{} Internal Server Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.VALIDATION_ERROR_STATUS,
                ResponseCodesAndMessages.VALIDATION_ERROR_CODE,
                ResponseCodesAndMessages.VALIDATION_ERROR_MESSAGE,
                null
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
    }

}
