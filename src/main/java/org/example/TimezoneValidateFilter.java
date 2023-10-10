package org.example;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        if (request instanceof HttpServletRequest req && response instanceof HttpServletResponse resp) {
            if (req.getQueryString() != null && req.getQueryString().startsWith("timezone=")) {
                var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                dateFormat.setTimeZone(TimeConverter.parseTimeZoneOffset(req.getQueryString().substring(9)));
                resp.getWriter().write(String.format("<h1>%s</h1>", dateFormat.format(new Date())));
                resp.getWriter().close();
            } else {
                resp.getWriter().write("<h1>Invalid timezone</h1>");
                resp.setStatus(400);
                resp.getWriter().close();
            }
        }
    }
}