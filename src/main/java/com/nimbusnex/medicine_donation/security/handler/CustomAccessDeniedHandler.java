package com.nimbusnex.medicine_donation.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthorizeRecordRequest;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.UnauthenticateResponse;
import com.nimbusnex.medicine_donation.model.response.UnauthorizeResponse;
import com.nimbusnex.medicine_donation.security.service.ErrorService;
import com.nimbusnex.medicine_donation.util.ResponseCodesAndMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ErrorService errorService;

    @Autowired
    public CustomAccessDeniedHandler(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        CommonResponse<UnauthorizeResponse> commonResponse = new CommonResponse<>();
        commonResponse.setStatus(ResponseCodesAndMessages.UNAUTHORIZED_ERROR_STATUS);
        commonResponse.setCode(ResponseCodesAndMessages.UNAUTHORIZED_ERROR_CODE);
        commonResponse.setMessage(ResponseCodesAndMessages.UNAUTHORIZED_ERROR_MESSAGE);

        UnauthorizeResponse unauthorizeResponse = new UnauthorizeResponse(
                LocalDateTime.now().toString(),
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                "Access denied: You don't have permission to access this resource",
                request.getRequestURI()
        );

        commonResponse.setData(unauthorizeResponse);

        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null;
        LOGGER.warn("Access denied for user: {}, path: {}", username, request.getRequestURI());

        errorService.insertUnauthorizedAccessRecord(new InsertUnAuthorizeRecordRequest(
                commonResponse.getStatus(),
                commonResponse.getCode(),
                commonResponse.getMessage(),
                unauthorizeResponse.getTimestamp(),
                unauthorizeResponse.getMessage(),
                unauthorizeResponse.getPath(),
                username));

        response.getOutputStream().println(objectMapper.writeValueAsString(commonResponse));
    }
}
