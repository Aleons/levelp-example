package ru.levelp.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.model.Event;

import java.util.Date;
import java.util.List;

@RestController
public class EventsListApi {
    @Autowired
    private EventsDAO eventsDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/api/events/all")
    public EventsListBean eventsList(@RequestHeader("User-Agent") String userAgent) {
        List<Event> events = eventsDAO.findEvents(new Date());
        return new EventsListBean(userAgent, events);
    }
}
