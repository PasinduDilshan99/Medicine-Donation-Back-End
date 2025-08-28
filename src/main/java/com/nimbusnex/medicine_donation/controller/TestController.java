package com.nimbusnex.medicine_donation.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v0/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(path = "/greet")
    public String test() {
        LOGGER.info("Test endpoint called");
        return "Hello";
    }

    @GetMapping(path = "/sessionId")
    public String sessionId(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getSession().getId();
        LOGGER.info("Session Id : {}", sessionId);
        return "Session Id : " + sessionId;
    }

    @GetMapping(path = "/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

}
