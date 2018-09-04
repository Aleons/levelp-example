package ru.levelp.example.web;

import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.EventsDAOImpl;
import ru.levelp.example.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = { "/events/all" })
public class EventsListServlet extends HttpServlet {
    private EventsDAO dao = new EventsDAOImpl(ApplicationListener.getEntityManager());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");
        List<Event> events = dao.findEvents(new Date());

        EventsListBean bean = new EventsListBean(userAgent, events);

        req.setAttribute("bean", bean);

        req.getRequestDispatcher("/pages/event-list.jsp")
                .forward(req, resp);
    }
}
