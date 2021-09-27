package com.rokomari.videoapp.security;


import com.rokomari.videoapp.authentication.service.CustomUser;
import com.rokomari.videoapp.common.utils.Defs;
import com.rokomari.videoapp.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationHeaderInterceptor implements ClientHttpRequestInterceptor {
    private HttpServletRequest httpServletRequest = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationHeaderInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = "Bearer ";
        try {
            httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            if (SecurityContextHolder.getContext() != null
                    && SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
                CustomUser cUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (cUser != null) {
                    token += cUser.getToken();
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        String uri = request.getURI().toString();
        if (uri.contains("login")) {
            token = "";
        }
        request.getHeaders().add(Defs.SECURITY_HEADER, token);
        String clientIp = Utils.getClientIpAddress(httpServletRequest);
        LOGGER.info("IP:{}",clientIp);
        request.getHeaders().add(Defs.CLIENT_IP_ADDRESS, clientIp);
        ClientHttpResponse response = null;
        try {
            response = execution.execute(request, body);
        } catch (Throwable ex) {
            LOGGER.error("===AAA ERROR:", ex);
            if (ex instanceof java.net.UnknownHostException) {
                throw new RuntimeException("Connection error occurred. Please try again later.");
            }
            if (ex instanceof ResourceAccessException) {
                throw new RuntimeException("Connection error occurred. Please try again later.");
            }
        }
        if (response == null) {
            throw new RuntimeException("Connection error occurred. Please try again later.");
        }


        return response;
    }
}
