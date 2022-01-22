package com.renga.money.manager.common.security.service.impl;

import com.renga.money.manager.common.models.entities.UserDetailsEntity;
import com.renga.money.manager.common.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    //This is where the authentication verified
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsEntity userDetailsEntity = userDetailsRepository.findByEmail(username);
        if(Objects.isNull(userDetailsEntity)){
            throw new UsernameNotFoundException(username + " is not found in the system.");
        }
        return new User(userDetailsEntity.getEmail(), userDetailsEntity.getPassword(), userDetailsEntity.getRoleEntities());
    }
}
