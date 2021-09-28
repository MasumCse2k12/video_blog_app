package com.rokomari.videoapp.idm.service;

import com.rokomari.videoapp.common.base.BaseRestTemplate;
import com.rokomari.videoapp.idm.payload.User;
import com.rokomari.videoapp.idm.payload.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceManager extends BaseRestTemplate {
    @Autowired
    RestTemplate restTemplate;

    public UserResponse crateUser(User idmUser) throws Throwable {
        String url = getUrl()+"/user/add";
        return restTemplate.postForObject(url, idmUser, UserResponse.class);
    }
}
