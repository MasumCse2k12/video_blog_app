package com.rokomari.videoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ServletContext mcontext;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        String message = "";

        if(exception instanceof UsernameNotFoundException) {
            message = "CFU";
        } else if(exception instanceof BadCredentialsException) {
            message = "CUP";
        } else if(exception instanceof InsufficientAuthenticationException) {
            message = "CNV";
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+String.format("/login?error=true&msg=%s", message));
    }
}
