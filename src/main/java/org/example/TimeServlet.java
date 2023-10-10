package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<html><head><title>Current Time</title></head><body>");

        String timezoneParameter = req.getParameter("timezone");

        TimeZone timeZone = TimeZone.getTimeZone(timezoneParameter != null ? timezoneParameter : "UTC");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(timeZone);

        String currentTime = dateFormat.format(new Date());
        if (timezoneParameter != null && !timezoneParameter.isEmpty()) {
            currentTime += " " + "+" + timezoneParameter;
        }

        resp.getWriter().println("<h1>Current time:</h1>");
        resp.getWriter().println("<p>" + currentTime + "</p>");
        resp.getWriter().println("</body></html>");
    }
}






