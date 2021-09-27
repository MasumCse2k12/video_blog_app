package com.rokomari.videoapp.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private ServletContext mcontext;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password = "";
        if (mcontext != null) {
            password = (String) mcontext.getAttribute("the_pass");
        }
        List<GrantedAuthority> grantList = new ArrayList<>(); //TODO
        return new CustomUser("admin",passwordEncoder.encode(password), "token", grantList );
    }
}
