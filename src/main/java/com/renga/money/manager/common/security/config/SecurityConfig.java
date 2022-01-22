package com.renga.money.manager.common.security.config;

import com.renga.money.manager.common.filters.MMSecurityFilter;
import com.renga.money.manager.common.security.service.impl.UserDetailsSecurityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsSecurityServiceImpl userDetailsSecurityService;

    //This method configuring authentication manager..
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSecurityService);
    }

    //Ant matchers does not understand the "/" at the end of the urls ("/api/v1/users/"). whereas mvc matchers works with this.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.formLogin();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/api/v1/users").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/showRegistrationPage", "/registrationForm",
                        "/registrationResponsePage", "/showUserDetailsPage", "/userSearchPage", "/userDetails").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/register", "/searchUserDetails").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,  "/index" ).hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.GET, "/", "/login").permitAll()
                .mvcMatchers(HttpMethod.POST, "/submitForm").permitAll()
                .mvcMatchers("/api/v1/shutdown").denyAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("**/hello").permitAll()
                .anyRequest().denyAll()
                .and().csrf().disable()
                .logout().logoutSuccessUrl("/").invalidateHttpSession(true);

        // added for H2 console access
        http.headers().frameOptions().disable();
        http.addFilterBefore(new MMSecurityFilter(), BasicAuthenticationFilter.class);

    }

    // Once the authentication manager is configured in the configure method above, we can expose the manager as bean to other services like SecurityServiceImpl
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
