package com.renga.money.manager.common.security.service.impl;

import com.renga.money.manager.common.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        boolean result = authenticationToken.isAuthenticated();
        if(result){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        return result;
    }

}
