package com.nimbusnex.medicine_donation.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusnex.medicine_donation.model.request.InsertUnAuthenticateRecordRequest;
import com.nimbusnex.medicine_donation.model.response.CommonResponse;
import com.nimbusnex.medicine_donation.model.response.UnauthenticateResponse;
import com.nimbusnex.medicine_donation.security.service.ErrorService;
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
import java.util.Base64;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
    private final ErrorService errorService;

    @Autowired
    public CustomAuthenticationEntryPoint(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String username = null;
        String password = null;
        CommonResponse<UnauthenticateResponse> commonResponse = new CommonResponse<>();
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes);
            String[] parts = credentials.split(":", 2);
            username = parts[0];
            password = parts.length > 1 ? parts[1] : "";
            LOGGER.info("Decoded username: {}", username);
        }

        commonResponse.setStatus(ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_STATUS);
        commonResponse.setCode(ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_CODE);
        commonResponse.setMessage(ResponseCodesAndMessages.UNAUTHENTICATED_ERROR_MESSAGE);
        UnauthenticateResponse unauthenticateResponse = new UnauthenticateResponse(
                LocalDateTime.now().toString(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                "Authentication failed: " + authException.getMessage(),
                request.getRequestURI()
        );
        commonResponse.setData(unauthenticateResponse);
        errorService.insertUnAuthenticateRecord(new InsertUnAuthenticateRecordRequest(
                commonResponse.getStatus(),
                commonResponse.getCode(),
                commonResponse.getMessage(),
                unauthenticateResponse.getTimestamp(),
                unauthenticateResponse.getMessage(),
                unauthenticateResponse.getPath(),
                username,
                password
        ));

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(commonResponse));
    }
}