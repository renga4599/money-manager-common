package com.renga.money.manager.common.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class CustomUserNamePasswordAuthenticationProviderOld implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("CustomUserNamePasswordAuthenticationProvider - authenticate - starts ");
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if("renga4".equalsIgnoreCase(userName) && "renga4".equalsIgnoreCase(password)){
            return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
        }
        throw new BadCredentialsException("Invalid user name or password");
    }

    //At run time authentication manager needs to evaluate all the authentication providers.
    // While doing so, it will pass the authentication type (eg UsernamePasswordAuthenticationToken) in the authentication object (parameter) to this method to understand if the authentication provider supports the specific type.
    // In this case it is  UsernamePasswordAuthenticationToken.class as it is returned in the above method 'authenticate'
    @Override
    public boolean supports(Class<?> authentication) {
        log.info("CustomUserNamePasswordAuthenticationProvider - supports - starts ");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
