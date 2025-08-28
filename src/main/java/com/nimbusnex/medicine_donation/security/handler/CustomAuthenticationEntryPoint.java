package com.nimbusnex.medicine_donation.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusnex.medicine_donation.model.request.InsertJwtTokenErrorRecordRequest;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.JwtErrorResponse;
import com.nimbusnex.medicine_donation.model.response.UnauthenticateResponse;
import com.nimbusnex.medicine_donation.security.service.ErrorService;
import com.nimbusnex.medicine_donation.security.service.JwtService;
import com.nimbusnex.medicine_donation.util.ResponseCodesAndMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ErrorService errorService;
    private final JwtService jwtService;

    @Autowired
    public CustomAuthenticationEntryPoint(ErrorService errorService, JwtService jwtService) {
        this.errorService = errorService;
        this.jwtService = jwtService;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String jwtError = (String) request.getAttribute("jwt.error");
        if (jwtError != null) {
            handleJwtError(request, response, jwtError);
        } else {
            handleBasicAuthError(request, response, authException);
        }
    }

    private void handleJwtError(HttpServletRequest request, HttpServletResponse response, String jwtError) throws IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        Long userId = null;
        String username = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwtToken = authHeader.substring(7);
                username = jwtService.extractUserName(jwtToken);
                userId = jwtService.extractUserId(jwtToken);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to parse JWT: {}", e.getMessage());
        }

        String errorMessage = "JWT Authentication failed: " + jwtError;
        LOGGER.warn("{} | path: {}", errorMessage, request.getRequestURI());

        JwtErrorResponse jwtErrorResponse = new JwtErrorResponse(
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                errorMessage,
                request.getRequestURI(),
                jwtToken
        );

        CommonResponse<JwtErrorResponse> commonResponse = buildCommonResponse(
                ResponseCodesAndMessages.JWT_FAILED_STATUS,
                ResponseCodesAndMessages.JWT_FAILED_CODE,
                ResponseCodesAndMessages.JWT_FAILED_MESSAGE,
                jwtErrorResponse
        );

        if (jwtToken != null) {
            errorService.insertJwtTokenErrorRecord(new InsertJwtTokenErrorRecordRequest(
                    commonResponse.getStatus(),
                    commonResponse.getCode(),
                    commonResponse.getMessage(),
                    jwtErrorResponse.getTimestamp(),
                    jwtErrorResponse.getMessage(),
                    jwtErrorResponse.getPath(),
                    userId != null ? String.valueOf(userId) : null,
                    jwtToken
            ));
        }

        writeResponse(response, commonResponse);
    }

    private void handleBasicAuthError(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String username = null;
        String password =null;

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                String base64Credentials = authHeader.substring(6).trim();
                String credentials = new String(Base64.getDecoder().decode(base64Credentials));
                String[] parts = credentials.split(":", 2);
                username = parts[0];
                password = parts[1];
            } catch (Exception e) {
                LOGGER.warn("Failed to decode Basic Auth header: {}", e.getMessage());
            }
        }

        String errorMessage = "Authentication failed: " + authException.getMessage();
        LOGGER.warn("{} | path: {}", errorMessage, request.getRequestURI());

        UnauthenticateResponse unauthResponse = new UnauthenticateResponse(
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                errorMessage,
                request.getRequestURI()
        );

        CommonResponse<UnauthenticateResponse> commonResponse = buildCommonResponse(
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_STATUS,
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_CODE,
                ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_MESSAGE,
                unauthResponse
        );

        errorService.insertUnAuthenticateRecord(new InsertUnAuthenticateRecordRequest(
                commonResponse.getStatus(),
                commonResponse.getCode(),
                commonResponse.getMessage(),
                unauthResponse.getTimestamp(),
                unauthResponse.getMessage(),
                unauthResponse.getPath(),
                username,
                password
        ));

        writeResponse(response, commonResponse);
    }

    private <T> CommonResponse<T> buildCommonResponse(int status, String code, String message, T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setStatus(status);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    private <T> void writeResponse(HttpServletResponse response, CommonResponse<T> commonResponse) throws IOException {
        response.getWriter().write(objectMapper.writeValueAsString(commonResponse));
    }
}
