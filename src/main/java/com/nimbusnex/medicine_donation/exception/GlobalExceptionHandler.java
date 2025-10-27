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
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {InternalServerErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handleInternalServerErrorException(InternalServerErrorExceptionHandler e) {
        logger.error("{} Internal Server Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);

        CommonResponse<String> commonResponse = new CommonResponse<>(
                ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_STATUS,
                ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_CODE,
                ResponseCodesAndMessages.INTERNAL_SERVER_ERROR_MESSAGE,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnAuthenticateErrorExceptionHandler.class})
    public <T> ResponseEntity<CommonResponse<List<T>>> handleUnAuthenticateErrorException(UnAuthenticateErrorExceptionHandler e) {
        logger.error("{} Un Authenticate Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_STATUS,
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_CODE,
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_MESSAGE,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InsertDataErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerInsertDataErrorException(InsertDataErrorExceptionHandler e) {
        logger.error("{} Insert Data Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UpdateDataErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerUpdateDataErrorException(UpdateDataErrorExceptionHandler e) {
        logger.error("{} Update Data Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {DeleteDataErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerDeleteDataErrorException(DeleteDataErrorExceptionHandler e) {
        logger.error("{} Delete Data Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {SearchDataErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerSearchDataErrorException(SearchDataErrorExceptionHandler e) {
        logger.error("{} Search Data Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {NoAnyDataFoundErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerNoAnyDataFoundErrorException(NoAnyDataFoundErrorExceptionHandler e) {
        logger.error("{} No Any Data Found Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {PreviousDataAndNewDataSameErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerPreviousDataAndNewDataSameErrorException(PreviousDataAndNewDataSameErrorExceptionHandler e) {
        logger.error("{} Previous Data And New Data Same Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidStatusCodeErrorExceptionHandler.class})
    public ResponseEntity<CommonResponse<String>> handlerInvalidStatusCodeErrorException(InvalidStatusCodeErrorExceptionHandler e) {
        logger.error("{} Invalid Status Code Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        CommonResponse commonResponse = new CommonResponse(
                ResponseCodesAndMessages.BAD_REQUEST_STATUS,
                ResponseCodesAndMessages.BAD_REQUEST_CODE,
                ResponseCodesAndMessages.BAD_REQUEST_MESSAGES,
                e.getMessage()
        );
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

}
