package com.rokomari.videoapp.authentication.service;

import com.rokomari.videoapp.common.utils.Utils;
import com.rokomari.videoapp.idm.payload.LoginRequest;
import com.rokomari.videoapp.idm.payload.LoginResponse;
import com.rokomari.videoapp.idm.service.AuthenticationServiceManager;
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
    @Autowired
    private AuthenticationServiceManager authManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String password = "";
        if (mcontext != null) {
            password = (String) mcontext.getAttribute("the_pass");
        }
        LoginRequest req = new LoginRequest();
        req.setUsername(userName);
        req.setPassword(password);
        LoginResponse resp;

        try {
            resp = authManager.login(req);
            LOGGER.info("STATUS:"+resp.isSuccess());
            if (!resp.isSuccess()) {
                mcontext.setAttribute("c_error", Utils.isOk(resp.getMessage()) ? resp.getMessage() : "Invalid user or password");
                throw new UsernameNotFoundException("Invalid user or password");
            }

        } catch (Throwable e) {
            LOGGER.error("Exception occured while login : bad credentials! ");
//            e.printStackTrace();
            if (e != null && Utils.isOk(e.getMessage()) &&e.getMessage().contains("401")) {
                mcontext.setAttribute("c_error", "Internal server error!");
            }else{
                mcontext.setAttribute("c_error", "Invalid username or password!");
            }
            throw new UsernameNotFoundException("Exception occured while login : " + e.getMessage());
        }
        if (resp == null) {
            mcontext.setAttribute("c_error", "Invalid username or password!");
            throw new UsernameNotFoundException("User not found!");
        }

        List<GrantedAuthority> grantList = new ArrayList<>(); //TODO
        return new CustomUser(resp.getId(), userName,passwordEncoder.encode(password), resp.getAccessToken(), grantList );
    }
}
