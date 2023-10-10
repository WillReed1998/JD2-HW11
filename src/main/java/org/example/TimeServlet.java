package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("<html><head><title>Current Time</title></head><body>");

            var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            dateFormat.setTimeZone(TimeConverter.parseTimeZoneOffset(req.getQueryString().substring(9)));
            String formattedTime = dateFormat.format(new Date());
            resp.getWriter().println("<h1>Current time:</h1>");
            resp.getWriter().println("<p>" + formattedTime + "</p>");
            resp.getWriter().println("</body></html>");
        }
    }
