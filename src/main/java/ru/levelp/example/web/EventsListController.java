package ru.levelp.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.example.dao.EventsDAO;
import ru.levelp.example.model.Event;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EventsListController {
    @Autowired
    private EventsDAO eventsDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/events/all")
    public String eventsList(@RequestHeader("User-Agent") String userAgent, ModelMap model) {
        List<Event> events = eventsDAO.findEvents(new Date());

        EventsListBean bean = new EventsListBean(userAgent, events);

        model.put("bean", bean);

        return "event-list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/events/add")
    public String addEventShowForm() {
        return "event-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/events/add")
    public String addEventPostForm(@RequestParam("title") String title,
                                   @RequestParam String description,
                                   @RequestParam String start,
                                   @RequestParam String end,
                                   @RequestHeader("User-Agent") String userAgent,
                                   ModelMap model) {
        if (title == null) {
            throw new IllegalArgumentException("title is missing");
        }
        if (description == null) {
            throw new IllegalArgumentException("description is missing");
        }

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

        return eventsList(userAgent, model);
    }
}
