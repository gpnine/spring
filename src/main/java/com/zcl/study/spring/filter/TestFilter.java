package com.zcl.study.spring.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (false) {
            httpServletResponse.sendError(400, "无管理员访问权限");
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
