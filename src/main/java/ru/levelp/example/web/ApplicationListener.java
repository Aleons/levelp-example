package ru.levelp.example.web;

import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.dao.EventsDAOImpl;
import ru.levelp.example.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("ProdPersistenceUnit");
        em = emf.createEntityManager();

        EventsDAO dao = new EventsDAOImpl(em);

        if (dao.findEvents(new Date()).isEmpty()) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Event event = new Event("Test", "test event", date, date);
            dao.add(event);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (em != null) em.close();
        em = null;
        if (emf != null) emf.close();
        emf = null;
    }
}
