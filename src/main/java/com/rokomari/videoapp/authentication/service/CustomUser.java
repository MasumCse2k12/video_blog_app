/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokomari.videoapp.authentication.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Asus
 */
@Getter
@Setter
public class CustomUser extends User {
    private Integer userId;
    private String userName;
    private String nameEn;
    private String token;
    private Long sessionTimeout;
    private String phone;
    private String email;
    private Boolean enabled;
    private boolean userActivated;
    
    public CustomUser(Integer userId,String username, String password, String token, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.token = token;
        this.userName = username;
        this.userId = userId;
    }
}
