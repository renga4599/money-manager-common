package com.renga.money.manager.common.security.config;

import com.renga.money.manager.common.filters.MMSecurityFilter;
import com.renga.money.manager.common.security.service.impl.CustomUserNamePasswordAuthenticationProviderOld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
//@Configuration
public class SecurityConfigCustomAuthenticationProviderOld1 extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.name:renga3}")
    private String userName;

    @Value("${spring.security.user.password:renga3}")
    private String password;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserNamePasswordAuthenticationProviderOld customUserNamePasswordAuthenticationProvider;


/*
// This method uses in-built authentication provider and it is used to add the UserDetailsService and Password encoder and authentication provider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.info("userName {} password {}", userName, password);
        //We need to store the password encoded in the memory as Spring compares the encoded password from the memory with the encoded password entered by user.
        UserDetails user = User.withUsername(userName).password(passwordEncoder.encode(password)).authorities("read").build();

        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
        userDetailService.createUser(user);

        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(customUserNamePasswordAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Start making changes to config 9 *********************************");
//        http.formLogin();
        http.httpBasic();
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("**/hello").permitAll()
                .antMatchers("/api/v1/shutdown").denyAll()
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().cors().and().csrf().disable();

        // added for H2 console access
        http.headers().frameOptions().disable();
        http.addFilterBefore(new MMSecurityFilter(), BasicAuthenticationFilter.class);
    }
}
