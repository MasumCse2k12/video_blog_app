package com.rokomari.videoapp.idm.service;

import com.rokomari.videoapp.common.base.BaseRestTemplate;
import com.rokomari.videoapp.idm.payload.LoginRequest;
import com.rokomari.videoapp.idm.payload.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceManager extends BaseRestTemplate {
    @Autowired
    RestTemplate restTemplate;

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        LoginResponse lr;
        String url = getUrl() + "/auth/login";
        System.out.println("url: " + url);
        lr = restTemplate.postForObject(url, loginRequest, LoginResponse.class);
        return lr;
    }
}
