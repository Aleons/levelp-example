package ru.levelp.example.web;

import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.EventsDAOImpl;
import ru.levelp.example.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/events/add")
public class EventsAddServlet extends HttpServlet {
    private EventsDAO eventsDAO = new EventsDAOImpl(
            ApplicationListener.getEntityManager()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/event-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (title == null) {
            throw new IllegalArgumentException("title is missing");
        }
        if (description == null) {
            throw new IllegalArgumentException("description is missing");
        }
        // ...

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDate;
        Date endDate;

        try {
            startDate = format.parse(start);
            endDate = format.parse(end);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date");
        }

        Event event = new Event(title, description, startDate, endDate);
        eventsDAO.add(event);

        resp.sendRedirect("/events/all");
    }
}
