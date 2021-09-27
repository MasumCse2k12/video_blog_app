/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokomari.videoapp.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 *
 * @author ISTL
 */
@Order(value = 1)
public class SecurityInitializer extends
        AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);

        // CSRF for multipart form data filter:
        FilterRegistration.Dynamic springMultipartFilter;
        springMultipartFilter = servletContext.addFilter(
                "springMultipartFilter", new MultipartFilter());
        springMultipartFilter.addMappingForUrlPatterns(null, false, "/*");

    }
        
}
