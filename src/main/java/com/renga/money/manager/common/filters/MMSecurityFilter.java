package com.renga.money.manager.common.filters;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class MMSecurityFilter implements Filter {

    //This filter can be used for debugging purpose...

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {
        log.info("MMSecurityFilter - Before ********************* ");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("MMSecurityFilter - After **************** ");
    }
}
