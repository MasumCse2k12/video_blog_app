/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokomari.videoapp.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Masum
 */
public class CommonPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String rawpswd = (String) rawPassword;
        return BCrypt.hashpw(rawpswd, BCrypt.gensalt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean status =  BCrypt.checkpw((String) rawPassword, encodedPassword);
        return status;
    }
    
}
